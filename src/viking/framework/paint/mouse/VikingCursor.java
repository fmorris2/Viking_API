package viking.framework.paint.mouse;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import viking.framework.paint.image.VImage;

public class VikingCursor
{
	private static final String INNER_CURSOR_URL = "http://vikingscripts.io/script/cursor_inner.png";
	private static final String OUTER_CURSOR_URL = "http://vikingscripts.io/script/cursor_outer.png";
	
	private static final int OFFSET_X = 9;
	private static final int OFFSET_Y = 9;
	
	private static final double ANGLE_CHANGE = 1;
	
	private VImage innerImage;
	private VImage outerImage;
	private AffineTransform originalTransform;
	private AffineTransform transform;
	private int angle;
	
	public VikingCursor()
	{
		innerImage = new VImage(INNER_CURSOR_URL);
		outerImage = new VImage(OUTER_CURSOR_URL);
		transform = new AffineTransform();
		angle = 0;
	}
	
	public void draw(Graphics2D g, Point mousePos)
	{
		originalTransform = g.getTransform();
		
		setRotation(g, false);
		innerImage.draw(g, mousePos.x - OFFSET_X, mousePos.y - OFFSET_Y);
		
		setRotation(g, true);
		outerImage.draw(g, mousePos.x - OFFSET_X, mousePos.y - OFFSET_Y);
		
		g.setTransform(originalTransform);
		angle += ANGLE_CHANGE;
	}
	
	private void setRotation(Graphics2D g, boolean inverse)
	{
		transform.rotate(Math.toRadians(angle * (inverse ? -1 : 1)));
		g.setTransform(transform);
	}
}
