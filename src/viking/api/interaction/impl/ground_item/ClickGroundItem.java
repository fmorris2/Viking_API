package viking.api.interaction.impl.ground_item;

import org.osbot.rs07.api.EntityAPI;
import org.osbot.rs07.api.filter.ActionFilter;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.filter.NameFilter;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.GroundItem;

import viking.api.filter.VFilters;
import viking.api.interaction.EntityInteraction;
import viking.framework.VMethodProvider;

public class ClickGroundItem extends EntityInteraction<GroundItem>
{

	public ClickGroundItem(VMethodProvider vmp, String action, String name, int searchDistance)
	{
		super(vmp, action, name, searchDistance);
	}
	
	public ClickGroundItem(VMethodProvider vmp, String action, GroundItem item)
	{
		super(vmp, action, item);
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
		return VFilters.and(new NameFilter<GroundItem>(name), new ActionFilter<GroundItem>(action));
	}

	@Override
	protected EntityAPI<GroundItem> getAPI()
	{
		return vmp.groundItems;
	}

}
