package viking.framework.paint.container.component;

import java.awt.Graphics2D;

import viking.framework.paint.container.VContainer;

public abstract class VComponent
{
	protected VContainer container;
	protected int x;
	protected int y;
	protected boolean isVisible;
	
	public VComponent(VContainer container)
	{
		this.container = container;
	}
	
	public abstract void draw(Graphics2D g);
	public abstract void reset();
	
	//getters & serrers
	public boolean isVisible()
	{
		return isVisible;
	}
}
