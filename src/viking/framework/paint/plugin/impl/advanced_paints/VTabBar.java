package viking.framework.paint.plugin.impl.advanced_paints;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.plugin.Drawable;

public class VTabBar extends ArrayList<VPaintTab> implements Drawable
{
	private static final long serialVersionUID = -6618295192075505153L;
	private static final int TAB_FONT_SIZE = 11;
	
	private VikingPaint<?> paint;
	private Font tabFont;
	
	public VTabBar(VikingPaint<?> paint, VPaintTab... tabs)
	{
		this.paint = paint;
		this.addAll(Arrays.asList(tabs));
		tabFont = paint.getFont().MEDIUM.get(TAB_FONT_SIZE);
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		drawTabs(g);
		
		//TODO draw the actual tab bar (names of tabs along with separators)
		
		//TODO draw the current selected tab
	}
	
	public void reset()
	{
		for(VPaintTab t : this)
			t.reset();
	}
	
	private void drawTabs(Graphics2D g)
	{
		g.setFont(tabFont);
		g.setColor(VChatBoxMinimal.DEFAULT_COLOR);
		
		
	}
}
