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
	private AffineTransform transform;
	private int angle;
	
	public VikingCursor()
	{
		innerImage = new VImage(INNER_CURSOR_URL);
		outerImage = new VImage(OUTER_CURSOR_URL);
		angle = 0;
	}
	
	public void draw(Graphics2D g, Point mousePos)
	{
		transform = g.getTransform();
		
		drawRotatingPart(g, transform, innerImage, mousePos, true);
		drawRotatingPart(g, transform, outerImage, mousePos, false);
		
		angle += ANGLE_CHANGE;
	}
	
	private void drawRotatingPart(Graphics2D g, AffineTransform transform, VImage image, Point mousePos, boolean clockwise)
	{
		final double ROTATION = calcRotation(!clockwise);
		
		transform.rotate(ROTATION);
		image.draw(g, mousePos.x - OFFSET_X, mousePos.y - OFFSET_Y);
		transform.rotate(ROTATION * -1);
	}
	
	private double calcRotation(boolean inverse)
	{
		return Math.toRadians(angle * (inverse ? -1 : 1));
	}
}
