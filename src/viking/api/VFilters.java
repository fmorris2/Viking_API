package viking.api;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Character;

import viking.framework.script.VikingScript;

/**
 * This class holds various pre-made filters for use in Viking scripts
 * 
 * @author The Viking
 */
public class VFilters extends ScriptUtil
{	
	public VFilters(VikingScript script)
	{
		super(script);
	}
	
	
	public Filter<? extends Character<?>> ableToAttack()
	{
		return (Character<?> f) -> f.isUnderAttack() && !f.getInteracting().equals(myPlayer());
	}
}
