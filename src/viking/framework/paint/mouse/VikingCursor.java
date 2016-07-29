package viking.framework.paint.mouse;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import viking.framework.paint.image.VImage;
import viking.framework.script.VikingScript;

public class VikingCursor
{	
	private static final int OFFSET_X = 10;
	private static final int OFFSET_Y = 10;
	private static final double ANGLE_CHANGE = 5;
	private static final float CURSOR_ALPHA = 0.80F;
	private static final Composite ALPHA_COMPOSITE = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, CURSOR_ALPHA);
	
	private final String INNER_CURSOR_URL;
	private final String OUTER_CURSOR_URL;
	
	private VImage innerImage;
	private VImage outerImage;
	private int angle;
	
	public VikingCursor(VikingScript script)
	{
		INNER_CURSOR_URL = script.getVikingWebsite() + "/script/cursor_inner.png";
		OUTER_CURSOR_URL = script.getVikingWebsite() + "/script/cursor_outer.png";
		innerImage = new VImage(INNER_CURSOR_URL);
		outerImage = new VImage(OUTER_CURSOR_URL);
	}
	
	public void draw(Graphics2D g, Point mousePos)
	{	
		drawRotatingPart(g, innerImage, mousePos, true);
		drawRotatingPart(g, outerImage, mousePos, false);
		
		angle += ANGLE_CHANGE;
	}
	
	private void drawRotatingPart(Graphics2D g, VImage image, Point mousePos, boolean clockwise)
	{
		//cache old graphics components
		AffineTransform old = g.getTransform();
		Composite oldComposite = g.getComposite();
		
		//set new graphics components & rotate image
		g.rotate(calcRotation(clockwise), mousePos.x, mousePos.y);
		g.setComposite(ALPHA_COMPOSITE);
		
		//draw our image
		image.draw(g, mousePos.x - OFFSET_X, mousePos.y - OFFSET_Y);
		
		//reset graphics components
		g.setComposite(oldComposite);
		g.setTransform(old);
	}
	
	private double calcRotation(boolean clockwise)
	{
		if(angle >= 360)
			angle -= 360;
		
		return Math.toRadians(angle * (clockwise ? 1 : -1));
	}
}
