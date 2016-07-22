package viking.api.interaction.impl;

import org.osbot.rs07.api.EntityAPI;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.filter.NameFilter;
import org.osbot.rs07.api.filter.ActionFilter;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;

import viking.api.filter.VFilters;
import viking.api.interaction.EntityInteraction;
import viking.framework.VMethodProvider;

public class ClickNpc extends EntityInteraction<NPC>
{
	public ClickNpc(VMethodProvider vmp, String action, NPC target)
	{
		super(vmp, action, target);
	}
	
	public ClickNpc(VMethodProvider vmp, String action, String name, int searchDistance)
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
		return VFilters.and(new NameFilter<NPC>(name), new ActionFilter<NPC>(action));
	}

	@Override
	protected EntityAPI<NPC> getAPI()
	{
		return vmp.npcs;
	}

}
