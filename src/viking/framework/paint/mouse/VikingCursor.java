package viking.framework.paint.mouse;

import java.awt.Graphics2D;
import java.awt.Point;

import viking.framework.paint.image.VImage;

public class VikingCursor
{
	private static final String INNER_CURSOR_URL = "http://vikingscripts.io/script/cursor_inner.png";
	private static final String OUTER_CURSOR_URL = "http://vikingscripts.io/script/cursor_outer.png";
	
	private static final int OFFSET_X = 9;
	private static final int OFFSET_Y = 9;
	
	private VImage innerImage;
	private VImage outerImage;
	
	public VikingCursor()
	{
		innerImage = new VImage(INNER_CURSOR_URL);
		outerImage = new VImage(OUTER_CURSOR_URL);
	}
	
	public void draw(Graphics2D g, Point mousePos)
	{
		innerImage.draw(g, mousePos.x - OFFSET_X, mousePos.y - OFFSET_Y);
		outerImage.draw(g, mousePos.x - OFFSET_X, mousePos.y - OFFSET_Y);
	}
}
