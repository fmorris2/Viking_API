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
	
	public void handle(Graphics2D g)
	{
		if(isVisible)
			draw(g);
	}
	
	public abstract void reset();

	protected abstract void draw(Graphics2D g);
	
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
