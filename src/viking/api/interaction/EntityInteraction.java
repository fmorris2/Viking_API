package viking.api.interaction;

import org.osbot.rs07.api.EntityAPI;
import org.osbot.rs07.api.filter.AreaFilter;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
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
	private final static int DIST_THRESH = 7;
	
	protected T target;
	protected Position fallBackPos; //Pos to walk to if web walking can't find a path to object tile bc object is on it
	protected EntityAPI<T> api;
	protected String name;
	protected String action;
	protected int searchDistance;
	protected VMethodProvider vmp;
	protected boolean needsToReach = true;
	private Filter<T> findFilter;
		
	public EntityInteraction(VMethodProvider vmp, int searchDistance)
	{
		this.vmp = vmp;
		this.searchDistance = searchDistance;
		api = getAPI();
		
	}
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
		
		if((needsToReach && !vmp.map.canReach(target)) || !target.isVisible() || vmp.myPosition().distance(target) > DIST_THRESH)
		{
			if(!vmp.walkUtils.walkTo(target.getPosition(), vmp.conditions.onScreenCondition(target).and(vmp.conditions.canReach(target)), null, 100, 100)
					&& fallBackPos != null)
			{
				vmp.log("[EntityInteraction] Attempting to walk to fall back position");
				vmp.walkUtils.walkTo(fallBackPos, vmp.conditions.onScreenCondition(target).and(vmp.conditions.canReach(target)), null, 100, 100);
			}
		}
		
		if(!target.isVisible())
			vmp.camera.toEntity(target);
		
		return (!needsToReach || vmp.map.canReach(target)) && target.isVisible();
	}
	
	public void setFallBackPos(Position p)
	{
		fallBackPos = p;
	}
	
	public void setNeedsToReach(boolean b)
	{
		needsToReach = b;
	}
}
