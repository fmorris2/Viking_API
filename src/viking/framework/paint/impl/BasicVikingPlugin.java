package viking.framework.paint.impl;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.VikingPaintPlugin;
import viking.framework.script.VikingScript;

/**
 * BasicVikingPaint is the most simple flavour of a VikingPaint. It pretty much
 * is just basic text above the chat box. No flashy effects / images.
 * 
 * @author The Viking
 *
 * @param <T> The type of VikingScript this paint is representing
 */
public abstract class BasicVikingPlugin<T extends VikingScript> extends VikingPaintPlugin<T>
{
	private final int PAINT_X; //The x coordinate for the paint text
	private final int PAINT_BOT_Y; //The y coordinate for the paint string on the bottom
	private final int PAINT_SPACE; //The space between paint fields		
	private final Composite ALPHA_COMPOSITE;
	
	private Color color;
	
	public BasicVikingPlugin(T script, VikingPaint<T> paint, Color color, float alpha, int x, int bottomY, int space)
	{
		super(script, paint);
		this.color = color;
		PAINT_X = x;
		PAINT_BOT_Y = bottomY;
		PAINT_SPACE = space;
		ALPHA_COMPOSITE = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
	}
	
	protected abstract String[] getInfo();
	
	@Override
	public void draw(Graphics2D g)
	{
		Composite oldComposite = g.getComposite();
		
		g.setColor(color);
		g.setComposite(ALPHA_COMPOSITE);
		
		String[] info = getInfo();
		
		for(int index = 0; index < info.length; index++)
			g.drawString(info[index], PAINT_X, PAINT_BOT_Y - (PAINT_SPACE * (info.length - (index + 1))));
		
		g.setComposite(oldComposite);
	}
}
