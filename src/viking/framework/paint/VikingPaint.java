package viking.framework.paint;

import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;

import viking.api.Timing;
import viking.framework.paint.mouse.VikingCursor;
import viking.framework.script.VikingScript;

/**
 * This is the base class we'll use for all of our paint systems.
 * It will contain all of the necessary method for any paint system
 * we decide to make. Scripts will simply program to this interface,
 * so we can swap out paint systems as we want.
 * 
 * @author The Viking
 *
 */
public abstract class VikingPaint<T extends VikingScript>
{
	protected T script;
	protected VikingFont font;
	protected VikingPaintPlugin[] plugins;
	protected VikingCursor cursor;
	protected long startTime;
	
	/**
	 * Primary constructor for VikingPaint
	 *
	 * @param script The VikingScript associated with this paint
	 */
	public VikingPaint(T script)
	{
		this.script = script;
		font = new VikingFont(script);
		plugins = generatePlugins(script);
		cursor = new VikingCursor(script);
		startTime = Timing.currentMs();
	}
	
	protected abstract VikingPaintPlugin[] generatePlugins(T script);
	
	/**
	 * Main paint method - Draws our paint on the screen
	 * 
	 * @param g The graphics2D object to draw the paint with
	 */
	public void paint(Graphics2D g)
	{
		if(!script.bot.lowCpu())
		{
			//anti-aliasing and all that jazz
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			rh.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			rh.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
			rh.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			rh.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			rh.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
			g.setRenderingHints(rh);
			
			//fonts
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font.NORMAL);
			g.setFont(font.NORMAL);
			
			//custom mouse cursor & trail
			cursor.draw(g, script.mouse.getPosition());
		}
		
		for(VikingPaintPlugin plugin : plugins)
			if(plugin.isVisible())
				plugin.draw(g);
	}
	
	public long getTimeRan()
	{
		return Timing.currentMs() - startTime;
	}
	
	public void reset()
	{
		startTime = Timing.currentMs();
		for(VikingPaintPlugin plugin : plugins)
			plugin.reset();
	}
}
