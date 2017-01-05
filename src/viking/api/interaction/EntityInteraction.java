package viking.api.interaction;

import org.osbot.rs07.api.EntityAPI;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Entity;

import viking.api.filter.VFilters;
import viking.framework.VMethodProvider;

/**
 * This is the base class for the Viking interaction
 * system.
 * 
 * @author The Viking
 *
 */
public abstract class EntityInteraction<T extends Entity>
{
	protected T target;
	protected EntityAPI<T> api;
	protected String name;
	protected String action;
	protected int searchDistance;
	protected VMethodProvider vmp;
	private Filter<T> findFilter;
	
	@SuppressWarnings("unchecked")
	public EntityInteraction(VMethodProvider vmp, String action, String name, int searchDistance)
	{
		this.action = action;
		this.name = name;
		this.searchDistance = searchDistance;
		this.vmp = vmp;
		findFilter = VFilters.and(findFilter(), vmp.filters.distanceFilter(searchDistance));
		api = getAPI();
	}
	
	public EntityInteraction(VMethodProvider vmp, String action, T target)
	{
		this.action = action;
		this.target = target;
		this.vmp = vmp;
		getAPI();
	}
	
	protected abstract boolean interact();
	protected abstract Filter<Entity> findFilter();
	protected abstract EntityAPI<T> getAPI();
	
	public boolean execute()
	{
		api.log("ENTITYINTERACTION TESSSSSTTTT");
		return (target == null ? findTarget() : true) && prepareInteraction() && interact();
	}
	
	@SuppressWarnings("unchecked")
	protected boolean findTarget()
	{
		return (target = api.closest(findFilter)) != null;
	}
	
	protected boolean prepareInteraction()
	{
		return target.isVisible() 
				|| vmp.camera.toEntity(target) 
				|| vmp.walkUtils.walkTo(target.getPosition(), vmp.conditions.onScreenCondition(target), null, -1, -1);
	}
}
