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
	
	private static final double ANGLE_CHANGE = 3;
	
	private VImage innerImage;
	private VImage outerImage;
	private int angle;
	
	public VikingCursor()
	{
		innerImage = new VImage(INNER_CURSOR_URL);
		outerImage = new VImage(OUTER_CURSOR_URL);
		angle = 0;
	}
	
	public void draw(Graphics2D g, Point mousePos)
	{	
		drawRotatingPart(g, innerImage, mousePos, true);
		drawRotatingPart(g, outerImage, mousePos, false);
		
		angle += ANGLE_CHANGE;
	}
	
	private void drawRotatingPart(Graphics2D g, VImage image, Point mousePos, boolean clockwise)
	{
		AffineTransform old = g.getTransform();
		final double ROTATION = calcRotation(!clockwise);
		final int X = mousePos.x - OFFSET_X;
		final int Y = mousePos.y - OFFSET_Y;
		
		g.rotate(ROTATION, X + image.getImage().getWidth() / 2, Y + image.getImage().getHeight() / 2);
		image.draw(g, X, Y);
		g.setTransform(old);
	}
	
	private double calcRotation(boolean clockwise)
	{
		if(angle >= 360)
			angle -= 360;
		
		return Math.toRadians(angle * (clockwise ? 1 : -1));
	}
}
