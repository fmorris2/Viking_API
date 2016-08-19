package viking.framework.paint.plugin.impl.advanced_paints;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import viking.framework.paint.plugin.Drawable;

public class VTabBar extends ArrayList<VPaintTab> implements Drawable
{
	private static final long serialVersionUID = -6618295192075505153L;
	private static final float TAB_LINE_ALPHA = 0.3F;
	private static final Composite ALPHA_COMPOSITE = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TAB_LINE_ALPHA);
	private static final Point LINE_START = new Point(301, 359);
	private static final Point LINE_END = new Point(495, 359);
	
	@Override
	public void draw(Graphics2D g)
	{
		drawTabLine(g);
		
		//TODO draw the actual tab bar (names of tabs along with separators)
		
		//TODO draw the current selected tab
	}
	
	public void reset()
	{
		for(VPaintTab t : this)
			t.reset();
	}
	
	private void drawTabLine(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		
		Composite oldComp = g.getComposite();
		g.setComposite(ALPHA_COMPOSITE);
		
		g.drawLine(LINE_START.x, LINE_START.y, LINE_END.x, LINE_END.y);
		g.setComposite(oldComp);
	}
}
