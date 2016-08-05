package viking.framework.paint.plugin.impl;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.plugin.VikingPaintPlugin;
import viking.framework.script.VikingScript;

/**
 * BasicVikingPaint is the most simple flavour of a VikingPaint. It pretty much
 * is just basic text above the chat box. No flashy effects / images.
 * 
 * @author The Viking
 *
 * @param <T> The type of VikingScript this paint is representing
 */
public abstract class BasicVikingPlugin extends VikingPaintPlugin
{
	private final static int SPACE_PADDING = 0; //Padding for space between lines of text in the paint
	
	private final int PAINT_X; //The x coordinate for the paint text
	private final int PAINT_BOT_Y; //The y coordinate for the paint string on the bottom
	private final Composite ALPHA_COMPOSITE;
	
	private Color color;
	private int paintSpace = -1;
	
	public BasicVikingPlugin(VikingScript script, VikingPaint<?> paint, Color color, float alpha, int x, int bottomY)
	{
		super(script, paint);
		this.color = color;
		PAINT_X = x;
		PAINT_BOT_Y = bottomY;
		ALPHA_COMPOSITE = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
	}
	
	protected abstract String[] getInfo();
	
	private int calculateSpace(Graphics2D g)
	{
		Font f = paint.getFont().getCurrent();
		FontMetrics metrics = g.getFontMetrics(f);
		
		return metrics.getHeight() + SPACE_PADDING;
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		if(paintSpace == -1) //Calculate space between lines if needed
			paintSpace = calculateSpace(g);
		
		Composite oldComposite = g.getComposite();
		
		g.setColor(color);
		g.setComposite(ALPHA_COMPOSITE);
		
		String[] info = getInfo();
		
		for(int index = 0; index < info.length; index++)
			g.drawString(info[index], PAINT_X, PAINT_BOT_Y - (paintSpace * (info.length - (index + 1))));
		
		g.setComposite(oldComposite);
	}
}
