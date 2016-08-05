package viking.framework.paint.container.component;

import java.awt.Graphics2D;

public abstract class VComponent
{
	protected int x;
	protected int y;
	protected boolean isVisible;
	
	public abstract void draw(Graphics2D g);
	public abstract void reset();
	
	//getters & serrers
	public boolean isVisible()
	{
		return isVisible;
	}
}
