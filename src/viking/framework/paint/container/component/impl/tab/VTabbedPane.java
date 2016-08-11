package viking.framework.paint.container.component.impl.tab;

import java.awt.Graphics2D;
import java.util.List;

import viking.framework.paint.container.VContainer;
import viking.framework.paint.container.component.VComponent;

public class VTabbedPane extends VComponent
{
	private int width;
	private int height;
	private List<VTab> tabs;
	private VTab currentlySelected;

	public VTabbedPane(VContainer container)
	{
		super(container, null);
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
