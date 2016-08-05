package viking.framework.paint.plugin;

import java.awt.Graphics2D;

import viking.framework.paint.VikingPaint;
import viking.framework.script.VikingScript;


public abstract class VikingPaintPlugin
{	
	protected VikingScript script;
	protected VikingPaint<?> paint;
	protected boolean isVisible;
	
	public VikingPaintPlugin(VikingScript script, VikingPaint<?> paint)
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
