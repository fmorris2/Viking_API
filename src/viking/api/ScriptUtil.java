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
	@SuppressWarnings("deprecation")
	public ScriptUtil(VikingScript script)
	{
		exchangeContext(script.bot);
	}
}
