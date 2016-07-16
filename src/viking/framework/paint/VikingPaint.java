package viking.framework.paint;

import java.awt.Graphics2D;

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
	
	/**
	 * Primary constructor for VikingPaint
	 *
	 * @param script The VikingScript associated with this paint
	 */
	public VikingPaint(T script)
	{
		this.script = script;
	}
	
	/**
	 * Main paint method - Draws our paint on the screen
	 * 
	 * @param g The graphics2D object to draw the paint with
	 */
	public abstract void paint(Graphics2D g);
	
	/**
	 * This method will grab the appropriate info to
	 * paint to the screen. For example, logs chopped,
	 * coins gained, etc etc
	 * 
	 * @return The paint info that we need to display
	 */
	public abstract String[] getInfo();
	
	/**
	 * This method resets the paint display. For example,
	 * when the user clicks the reset button, it will reset
	 * all tracked info to 0
	 */
	public abstract void reset();
}
