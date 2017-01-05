package viking.api.interaction.impl.object;

import org.osbot.rs07.api.EntityAPI;
import org.osbot.rs07.api.filter.ActionFilter;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.filter.NameFilter;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.RS2Object;

import viking.api.filter.VFilters;
import viking.api.interaction.EntityInteraction;
import viking.framework.VMethodProvider;

public class ClickObject extends EntityInteraction<RS2Object>
{

	public ClickObject(VMethodProvider vmp, String action, RS2Object target)
	{
		super(vmp, action, target);
	}
	
	public ClickObject(VMethodProvider vmp, String action, String name, int searchDistance)
	{
		super(vmp, action, name, searchDistance);
	}
	
	@Override
	protected boolean interact()
	{
		return target.interact(action);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Filter<Entity> findFilter()
	{
		return VFilters.and(new NameFilter<RS2Object>(name), new ActionFilter<RS2Object>(action));
	}

	@Override
	protected EntityAPI<RS2Object> getAPI()
	{
		return vmp.objects;
	}

}
