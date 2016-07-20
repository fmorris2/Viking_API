package viking.api;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.NPC;

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
}
