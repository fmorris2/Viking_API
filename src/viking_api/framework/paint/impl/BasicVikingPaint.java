package viking_api.framework.paint.impl;

import java.awt.Graphics2D;

import viking_api.framework.paint.VikingPaint;
import viking_api.framework.script.VikingScript;

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
	public BasicVikingPaint(T script)
	{
		super(script);
	}
	
	@Override
	public void paint(Graphics2D g)
	{
		
	}
}
