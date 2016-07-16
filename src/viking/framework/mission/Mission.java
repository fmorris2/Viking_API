package viking.framework.mission;

import viking.framework.goal.GoalList;
import viking.framework.script.VikingScript;

/**
 * This is the interface that represents a "mission", which is essentially
 * something to execute until specific goals have been reached.
 * 
 * @author The Viking
 */
public abstract class Mission
{
	protected VikingScript script;
	
	/**
	 * Primary constructor for Mission - Takes in the VikingScript
	 * relating to this mission
	 * 
	 * @param script The VikingScript driving this mission
	 */
	public Mission(VikingScript script)
	{
		this.script = script;
	}
	
	/**
	 * This method determines whether or not the mission can end
	 * 
	 * @return true if the mission can end, false if it needs to continue
	 */
	public abstract boolean canEnd();
	
	/**
	 * This method gets the mission name
	 * 
	 * @return A String representing the name of this mission
	 */
	public abstract String getMissionName();
	
	/**
	 * This method gets the current task name
	 * 
	 * @return A String representing the name of the current task
	 */
	public abstract String getCurrentTaskName();
	
	/**
	 * This method gets the message that should be displayed on the end
	 * of this mission
	 * 
	 * @return A String representing the end message
	 */
	public abstract String getEndMessage();
	
	/**
	 * Generates the goals for this mission
	 * 
	 * @return A GoalList object containing the goals for this mission, or null
	 * if there are no goals for this mission
	 */
	public abstract GoalList getGoals();
	
	/**
	 * Generates the mission-specific paint Strings for this mission
	 * This is combined with the VikingScript specific paint Strings
	 * seamlessly into one paint
	 * 
	 * @return An array of Strings representing the mission specific paint info,
	 * or null if there is none
	 */
	public abstract String[] getMissionPaint();
	
	/**
	 * This is the main method for the mission. Essentially the same as onLoop(),
	 * but for a mission
	 * 
	 * @return the sleep time for this cycle
	 */
	public abstract int execute();
	
	/**
	 * Resets the mission specific paint info
	 */
	public abstract void resetPaint();
	
	//Getters & Setters
	public VikingScript getScript()
	{
		return script;
	}
	
}
