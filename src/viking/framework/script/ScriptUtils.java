package viking.framework.script;

import viking.api.banking.BankUtils;

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
	
	public ScriptUtils(VikingScript script)
	{
		bank = new BankUtils(script);
	}
	
	//Getters
	public BankUtils getBank()
	{
		return bank;
	}
}
