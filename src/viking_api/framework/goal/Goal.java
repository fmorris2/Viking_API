package viking_api.framework.goal;

/**
 * This is the interface that represents what a goal is. A goal is
 * simply something that you're aiming to achieve. In this case, we
 * give a goal a name, it has a condition which represents what
 * you're aiming to achieve, and it has a messsage to send on completion
 * of the goal.
 * 
 * @author The Viking
 */
public interface Goal
{		
	/**
	 * Represents the name of this goal
	 * 
	 * @return a String, which represents the name of this goal
	 */
	public abstract String getName();
	
	/**
	 * Represents what we're trying to achieve with this goal
	 * 
	 * @return true if the goal has been reached, false if it's still in progress
	 */
	public abstract boolean hasReached();
	
	/**
	 * The message we send on completion of this goal
	 * 
	 * @return a String representing the message we send on completion of the goal
	 */
	public abstract String getCompletionMessage();
}
