package viking.framework.paint.plugin.impl.advanced_paints;

import java.awt.Graphics2D;
import java.util.ArrayList;

import viking.framework.paint.plugin.Drawable;

public class VTabBar extends ArrayList<VPaintTab> implements Drawable
{
	private static final long serialVersionUID = -6618295192075505153L;

	@Override
	public void handle(Graphics2D g)
	{
		//TODO draw the actual tab bar (names of tabs along with separators)
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		//TODO draw the current selected tab
	}
}
