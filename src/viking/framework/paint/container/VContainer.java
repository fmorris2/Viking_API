package viking.framework.paint.container;

import java.awt.Graphics2D;
import java.util.List;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.container.component.VComponent;
import viking.framework.paint.plugin.VikingPaintPlugin;
import viking.framework.script.VikingScript;

public class VContainer extends VikingPaintPlugin
{
	private int x;
	private int y;
	private int width;
	private int height;
	private List<VComponent> components;
	
	public VContainer(VikingScript script, VikingPaint<?> paint)
	{
		super(script, paint);
	}

	@Override
	public void draw(Graphics2D g)
	{
	}

	@Override
	public void reset()
	{
	}

}
