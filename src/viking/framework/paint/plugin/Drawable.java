package viking.framework.paint.plugin;

import java.awt.Graphics2D;

public interface Drawable
{
	public void draw(Graphics2D g);
	
	public void handle(Graphics2D g);
}
