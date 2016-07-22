package viking.api.filter;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;

import viking.api.ScriptUtil;
import viking.framework.script.VikingScript;

/**
 * This class holds various pre-made filters for use in Viking scripts
 * 
 * @author The Viking
 */
public class VFilters extends ScriptUtil
{
	public final Filter<NPC> ABLE_TO_ATTACK_NPC = ableToAttack();
	
	public VFilters(VikingScript script)
	{
		super(script);
	}
	
	/**
	 * Filter for determining whether or not an NPC is able to be attacked.
	 * Checks if the npc is not under attack, and also not interacting with
	 * any other character
	 * 
	 * @return a Filter which filters only NPCs that are viable for attacking
	 */
	private Filter<NPC> ableToAttack()
	{
		return (NPC f) -> !f.isUnderAttack() && f.getInteracting() == null && f.getHealthPercent() > 0;
	}
	
	/**
	 * Filter for determining whether or not an NPC is within
	 * a certain distance, inclusive
	 * 
	 * @param distance the maximum distance the npc can be away, inclusive
	 * @return a filter which filters only NPCs that are within a certain distance
	 */
	public Filter<Entity> distanceFilter(int distance)
	{
		return (Entity e) -> myPosition().distance(e) <= distance;
	}
	
	/**
	 * This method combines two VFilter objects into one,
	 * with an "AND" relationship
	 * 
	 * @param o the other VFilter object to combine with this one
	 * @return the new, combined VFilter object
	 */
	@SuppressWarnings("rawtypes")
	public static <T> Filter and(Filter<T> one, Filter<T> two)
	{	
		return new Filter<T>()
		{
			@Override
			public boolean match(T t)
			{
				return one.match(t) && two.match(t);
			}
		};
	}
	
	/**
	 * This method combines to VFilter objects into one,
	 * with an "OR" relationship
	 * 
	 * @param o the other VFilter object to combine with this one
	 * @return the new, combined VFilter object
	 */
	@SuppressWarnings("rawtypes")
	public static <T> Filter or(Filter<T> one, Filter<T> two)
	{	
		return new Filter<T>()
		{
			@Override
			public boolean match(T t)
			{
				return one.match(t) || two.match(t);
			}
		};
	}
}
