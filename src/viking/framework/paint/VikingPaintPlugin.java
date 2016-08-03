package viking.framework.paint;

import java.awt.Graphics2D;

import viking.framework.script.VikingScript;


public abstract class VikingPaintPlugin<T extends VikingScript>
{	
	protected T script;
	protected VikingPaint<T> paint;
	protected boolean isVisible;
	
	public VikingPaintPlugin(T script, VikingPaint<T> paint)
	{
		this.script = script;
		this.paint = paint;
		isVisible = true;
	}
	
	public abstract void draw(Graphics2D g);
	
	public abstract void reset();
	
	//Getters & Setters
	public boolean isVisible()
	{
		return isVisible;
	}
	
	public void setIsVisible(boolean b)
	{
		isVisible = b;
	}
}