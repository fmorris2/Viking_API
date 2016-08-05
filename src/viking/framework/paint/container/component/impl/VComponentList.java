package viking.framework.paint.container.component.impl;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import viking.framework.paint.container.VContainer;
import viking.framework.paint.container.component.VComponent;

public class VComponentList<T extends VComponent> extends VComponent
{
	private List<T> components;
	
	@SuppressWarnings("unchecked")
	public VComponentList(VContainer container, T... components)
	{
		super(container);
		this.components = new ArrayList<>(Arrays.asList(components));
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		for(T t : components)
			if(t.isVisible())
				t.draw(g);
	}

	@Override
	public void reset()
	{
		for(T t : components)
			t.reset();
	}

}
