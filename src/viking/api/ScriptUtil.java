package viking.api;

import viking.framework.VMethodProvider;
import viking.framework.script.VikingScript;

/**
 * Base class for all of the Viking script util objects
 * 
 * @author The Viking
 *
 */
public class ScriptUtil extends VMethodProvider
{
	protected VikingScript script;
	
	@SuppressWarnings("deprecation")
	public ScriptUtil(VikingScript script)
	{
		this.script = script;
		exchangeContext(script.bot);
	}
}
