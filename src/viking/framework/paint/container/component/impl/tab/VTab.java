package viking.framework.paint.container.component.impl.tab;

import java.awt.Font;
import java.awt.Graphics2D;

import viking.framework.paint.container.VContainer;
import viking.framework.paint.container.component.VComponent;

public class VTab extends VComponent
{
	private static final int PADDING_X = 0;
	private static final int PADDING_Y = 0;
	
	private String text;
	private int width;
	private int height;
	private VComponent content;
	
	public VTab(VContainer container, Font f)
	{
		super(container, f);
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
