package viking.api.travel;

import java.util.List;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.event.Event;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.event.WebWalkEvent;

import viking.api.Timing;
import viking.api.condition.LCondition;
import viking.api.condition.VCondition;
import viking.framework.VMethodProvider;

/**
 * This class will hold various utility method relating
 * to walking
 *
 * @author The Viking
 */
public class WalkingUtils extends VMethodProvider {
	private static final int LOCAL_WALK_THRESH = 20;
	
    private static final int MIN_LOCAL_COORD = 16;
    private static final int MAX_LOCAL_COORD = 50;

    /**
     * The general walking method in the WalkingUtils class.
     * This method will intelligently determine which walking system
     * provided by the API to use. Will use WebWalking if the distance
     * is far, or the default Walking class if the distance is shorter
     *
     * @param pos            the position to WALK to
     * @param breakCondition a condition to break out of the walking for
     * @param waitCondition  a condition to wait for after the walking finishes
     * @param cycleTime      the cycle time for the wait condition
     * @param timeout        the timeout for the wait condition
     * @return true if we've successfully walked to the position, false otherwise
     * @throws InterruptedException
     */
    public boolean walkTo(Position pos, VCondition breakCondition, VCondition waitCondition, int cycleTime, int timeout) {
        //determine if we'll use web walking or normal walking
        final boolean IS_REGIONAL = isRegional(pos);
        final boolean SHOULD_USE_NORMAL_WALK = IS_REGIONAL || (myPosition().distance(pos) < LOCAL_WALK_THRESH 
        		&& myPosition().getZ() == pos.getZ());
        
        Event walkEvent = SHOULD_USE_NORMAL_WALK ? new WalkingEvent(pos) : new WebWalkEvent(pos);
        
        if(SHOULD_USE_NORMAL_WALK)
        	script.log(this, false, "walkTo using normal walking");
        else
        	script.log(this, false, "walkTo using web walking");

        //set the break condition if necessary
        if (breakCondition != null) {
            if (SHOULD_USE_NORMAL_WALK)
                ((WalkingEvent) (walkEvent)).setBreakCondition(breakCondition);
            else
                ((WebWalkEvent) (walkEvent)).setBreakCondition(breakCondition);
        }
        
        Event event = execute(walkEvent);
        
        if(event.hasFailed() && SHOULD_USE_NORMAL_WALK)
        {
        	script.log(this, false, "Normal walk failed.. trying LocalPathFinder");
        	List<Position> path = localPathFinder.findPath(pos);
        	if(path != null)
        		walking.walkPath(path);
        }

        //execute the WALK event
        return event.hasFinished()
                && waitCondition == null ? true : Timing.waitCondition(waitCondition, cycleTime, timeout);
    }

    /**
     * This method is the most basic WALK method in this class. It calls upon
     * the general WALK method, but doesn't make use of a break condition or wait
     * condition.
     *
     * @param pos The position to WALK to
     * @return true if we've successfully walked to the position, false otherwise
     * @throws InterruptedException
     */
    public boolean walkTo(Position pos) {
        return walkTo(pos, null, null, -1, -1);
    }

    /**
     * This method calls upon the general walkTo method, but walks to a random position
     * in the area provided and also breaks out of the walking method as soon as the player
     * enters the area.
     *
     * @param a The area to WALK to
     * @return true if the player successfully walked to the area, false otherwise
     */
    public boolean walkToArea(Area a) {
        final VCondition IN_AREA = conditions.inAreaCondition(a);
        return walkTo(a.getRandomPosition(), IN_AREA, IN_AREA, 600, 3500);
    }

    /**
     * This method calls upon the general walkTo method, but walks to a random position
     * in the area provided and also breaks out of the walking method as soon as the player
     * enters the area.
     *
     * @param a         The area to WALK to
     * @param break_condition The stopping condition
     * @param wait_condition The waiting condition
     * @return true if the player successfully walked to the area, false otherwise
     */
    public boolean walkToArea(Area a, LCondition break_condition, LCondition wait_condition) {
        VCondition b_condition = new VCondition() {
            @Override
            public boolean evaluate() {
                return break_condition.evaluate();
            }
        };
        VCondition w_condition = new VCondition() {
            @Override
            public boolean evaluate() {
                return wait_condition.evaluate();
            }
        };
        return walkTo(a.getRandomPosition(), b_condition, w_condition, 600, 3500);
    }

    public boolean walkToArea(Area a, LCondition break_condition) {
        return walkToArea(a, break_condition, null);
    }

    /**
     * This method tells us whether a given position is in the
     * loaded region
     *
     * @param position the position to check
     * @return true if the position is in the loaded region, false otherwise
     */
    private boolean isRegional(Position position) {
        if (position == null)
            return false;

        int baseX = position.getX() - map.getBaseX();
        int baseY = position.getY() - map.getBaseY();
        boolean samePlane = position.getZ() == map.getPlane();

        return samePlane && (baseX >= MIN_LOCAL_COORD && baseX <= MAX_LOCAL_COORD &&
                baseY >= MIN_LOCAL_COORD && baseY <= MAX_LOCAL_COORD);
    }

}