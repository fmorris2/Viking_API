package viking.api;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.event.Event;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.event.WebWalkEvent;

import viking.api.condition.VCondition;
import viking.framework.script.VikingScript;

/**
 * This class will hold various utility method relating
 * to walking
 * 
 * @author The Viking
 *
 */
public class WalkingUtils extends ScriptUtil
{
	private static final int MIN_LOCAL_COORD = 16;
	private static final int MAX_LOCAL_COORD = 88;
	
	public WalkingUtils(VikingScript script)
	{
		super(script);
	}
	
	/**
	 * The general walking method in the WalkingUtils class.
	 * This method will intelligently determine which walking system
	 * provided by the API to use. Will use WebWalking if the distance
	 * is far, or the default Walking class if the distance is shorter
	 * 
	 * @param pos the position to walk to
	 * @param breakCondition a condition to break out of the walking for 
	 * @param waitCondition a condition to wait for after the walking finishes
	 * @param cycleTime the cycle time for the wait condition
	 * @param timeout the timeout for the wait condition
	 * @return true if we've successfully walked to the position, false otherwise
	 * @throws InterruptedException 
	 */
	public boolean walkTo(Position pos, VCondition breakCondition, VCondition waitCondition, int cycleTime, int timeout) throws InterruptedException
	{
		//determine if we'll use web walking or normal walking
		final boolean IS_REGIONAL = isRegional(pos);
		Event walkEvent = IS_REGIONAL ? new WalkingEvent(pos) : new WebWalkEvent(pos);
		
		//set the break condition if necessary
		if(breakCondition != null)
		{
			if(IS_REGIONAL)
				((WalkingEvent)(walkEvent)).setBreakCondition(breakCondition);
			else
				((WebWalkEvent)(walkEvent)).setBreakCondition(breakCondition);
		}
		
		//execute the walk event
		while(!walkEvent.hasFailed() && !walkEvent.hasFinished())
			VikingScript.sleep(walkEvent.execute());
		
		//wait if necessary
		return waitCondition == null ? walkEvent.hasFinished() : Timing.waitCondition(waitCondition, cycleTime, timeout);
	}
	
	/**
	 * This method tells us whether a given position is in the
	 * loaded region
	 * 
	 * @param position the position to check
	 * @return true if the position is in the loaded region, false otherwise
	 */
	private boolean isRegional(Position position) 
	{
        int baseX = position.getX() - script.map.getBaseX();
        int baseY = position.getY() - script.map.getBaseY();
        		
        return baseX >= MIN_LOCAL_COORD && baseX <= MAX_LOCAL_COORD &&
        		baseY >= MIN_LOCAL_COORD && baseY <= MAX_LOCAL_COORD;
    }
}
