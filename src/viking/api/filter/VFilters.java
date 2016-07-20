package viking.api.filter;

import org.osbot.T;
import org.osbot.rs07.api.filter.Filter;
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
		
	private Filter<NPC> ableToAttack()
	{
		return (NPC f) -> f.isUnderAttack() && !f.getInteracting().equals(myPlayer());
	}
	
	/**
	 * This method combines two VFilter objects into one,
	 * with an "AND" relationship
	 * 
	 * @param o the other VFilter object to combine with this one
	 * @return the new, combined VFilter object
	 */
	public static Filter<T> and(Filter<T> one, Filter<T> two)
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
	public static Filter<T> or(Filter<T> one, Filter<T> two)
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
