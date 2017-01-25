package viking.framework.paint.plugin.impl.advanced_paints;

import java.awt.Rectangle;

import viking.framework.paint.plugin.Drawable;

public abstract class VPaintTab implements Drawable
{
	protected String tabName;
	protected Rectangle rectangle;
	
	public abstract void reset();
	
	public String getTabName()
	{
		return tabName;
	}
}
