package viking.api.interaction;

import org.osbot.rs07.api.EntityAPI;
import org.osbot.rs07.api.filter.AreaFilter;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
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
	private static final int WALK_TO_THRESH = 9;
	
	protected T target;
	protected EntityAPI<T> api;
	protected String name;
	protected String action;
	protected int searchDistance;
	protected VMethodProvider vmp;
	private Filter<T> findFilter;
	
	
	public EntityInteraction(VMethodProvider vmp, String action)
	{
		this.vmp = vmp;
		this.action = action;
		api = getAPI();
	}
	
	@SuppressWarnings("unchecked")
	public EntityInteraction(VMethodProvider vmp, String action, String name, int searchDistance)
	{
		this(vmp, action);
		this.name = name;
		this.searchDistance = searchDistance;
		findFilter = VFilters.and(findFilter(), vmp.filters.distanceFilter(searchDistance));
	}
	
	public EntityInteraction(VMethodProvider vmp, String action, T target)
	{
		this(vmp, action);
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	public EntityInteraction(VMethodProvider vmp, String action, String name, Area a)
	{
		this(vmp, action);
		this.name = name;
		findFilter = VFilters.and(findFilter(), new AreaFilter<Entity>(a));
	}
	
	protected abstract boolean interact();
	protected abstract Filter<Entity> findFilter();
	protected abstract EntityAPI<T> getAPI();
	
	public boolean execute()
	{
		return (target == null ? findTarget() : true) && prepareInteraction() && interact();
	}
	
	@SuppressWarnings("unchecked")
	protected boolean findTarget()
	{
		return (target = api.closest(findFilter)) != null;
	}
	
	protected boolean prepareInteraction()
	{
		if(!target.isVisible())
			vmp.camera.toEntity(target);
		
		if(!target.isVisible() && vmp.myPosition().distance(target.getPosition()) > WALK_TO_THRESH)
			vmp.walkUtils.walkTo(target.getPosition(), vmp.conditions.onScreenCondition(target), null, 100, 100);
		
		if(!target.isVisible())
			vmp.camera.toEntity(target);
		
		return target.isVisible();
	}
}
