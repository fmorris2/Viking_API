package viking.framework.paint.impl;

import java.awt.Color;
import java.awt.Graphics2D;

import viking.framework.paint.VikingPaint;
import viking.framework.script.VikingScript;

/**
 * BasicVikingPaint is the most simple flavour of a VikingPaint. It pretty much
 * is just basic text above the chat box. No flashy effects / images.
 * 
 * @author The Viking
 *
 * @param <T> The type of VikingScript this paint is representing
 */
public abstract class BasicVikingPaint<T extends VikingScript> extends VikingPaint<T>
{
	private final int PAINT_X = 4; //The x coordinate for the paint text
	private final int PAINT_BOT_Y = 336; //The y coordinate for the paint string on the bottom
	private final int PAINT_SPACE = 15; //The space between paint fields		
	
	private Color color;
	
	public BasicVikingPaint(T script, Color color)
	{
		super(script);
		this.color = color;
	}
	
	@Override
	public void paint(Graphics2D g)
	{
		g.setColor(color);
		
		String[] info = getInfo();
		
		for(int index = 0; index < info.length; index++)
			g.drawString(info[index], PAINT_X, PAINT_BOT_Y - (PAINT_SPACE * (info.length - (index + 1))));
	}
}
