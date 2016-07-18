package viking.framework;

import org.osbot.rs07.script.MethodProvider;

import viking.api.WalkingUtils;
import viking.api.banking.BankUtils;
import viking.framework.script.VikingScript;

/**
 * This class serves as an extension of MethodProvider in the
 * OSBot API. It allows various classes of our framework (worker, mission)
 * to extend it, and therefore all of the important classes in our implementation
 * will have direct access to the various script utilities, instead of having to 
 * type extremely long chains of getters
 * 
 * @author The Viking
 *
 */
public class VMethodProvider extends MethodProvider
{	
	public VikingScript script;
	public BankUtils bankUtils;
	public WalkingUtils walkUtils;
	
	@SuppressWarnings("deprecation")
	public void exchangeContext(VikingScript script)
	{
		super.exchangeContext(script.bot);
		
		this.script = script;
		bankUtils = script.getUtils().getBank();
		walkUtils = script.getUtils().getWalk();
	}
}
