package viking.framework.script;

import viking.api.WalkingUtils;
import viking.api.banking.BankUtils;
import viking.api.condition.VConditions;

/**
 * Each VikingScript has a ScriptUtils object which hold various other
 * utility objects from the Viking API
 * 
 * @author The Viking
 *
 */
public class ScriptUtils
{	
	private BankUtils bank;
	private WalkingUtils walk;
	private VConditions conditions;
	
	public ScriptUtils(VikingScript script)
	{
		bank = new BankUtils(script);
		walk = new WalkingUtils(script);
		conditions = new VConditions(script);
	}
	
	//Getters
	public BankUtils getBank()
	{
		return bank;
	}
	
	public WalkingUtils getWalk()
	{
		return walk;
	}
	
	public VConditions getConditions()
	{
		return conditions;
	}
}
