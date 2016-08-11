package viking.framework.paint.container.component;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import viking.framework.paint.container.VContainer;

public abstract class VComponent
{
	protected VContainer container;
	protected int x;
	protected int y;
	protected boolean isVisible;
	protected Font font;
	
	public VComponent(VContainer container, Font font)
	{
		this.container = container;
		this.font = font;
	}
	
	public void handle(Graphics2D g)
	{
		if(isVisible)
		{
			//font
			if(font != null)
			{
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(font);
				g.setFont(font);
			}
			
			draw(g);
		}
	}
	
	protected abstract void draw(Graphics2D g);
	public abstract void reset();
	
	//getters & serrers
	public boolean isVisible()
	{
		return isVisible;
	}
}
