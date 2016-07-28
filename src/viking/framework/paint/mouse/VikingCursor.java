package viking.framework.paint.mouse;

import java.awt.Graphics2D;
import java.awt.Point;

import viking.framework.paint.image.VImage;

public class VikingCursor
{
	private static final String CURSOR_URL = "http://vikingscripts.io/script/cursor.png";
	private static final int OFFSET_X = 0;
	private static final int OFFSET_Y = 0;
	
	private VImage cursorImage;
	
	public VikingCursor()
	{
		cursorImage = new VImage(CURSOR_URL);
	}
	
	public void draw(Graphics2D g, Point mousePos)
	{
		cursorImage.draw(g, mousePos.x + OFFSET_X, mousePos.y + OFFSET_Y);
	}
}
