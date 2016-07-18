package viking.api.condition;

import org.osbot.rs07.api.map.Area;

import viking.api.ScriptUtil;
import viking.framework.script.VikingScript;

/**
 * This class holds all of the preset VConditions in
 * the Viking API. This cuts down on having to create
 * Condition code in the actual scripts, and also offers
 * a performance boost due to a lot of the conditions being
 * cached in constants
 * 
 * @author The Viking
 *
 */
public class VConditions extends ScriptUtil
{
	public VConditions(VikingScript script)
	{
		super(script);
	}

	public VCondition inAreaCondition(Area a)
	{
		return new VCondition()
		{
			@Override
			public boolean evaluate()
			{
				return a.contains(script.myPosition());
			}
			
		};
	}
}
