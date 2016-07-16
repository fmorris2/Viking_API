package viking.framework.mission;

import viking.framework.goal.GoalList;

/**
 * This is the interface that represents a "mission", which is essentially
 * something to execute until specific goals have been reached.
 * 
 * @author The Viking
 */
public interface Mission
{
	/**
	 * This method determines whether or not the mission can end
	 * 
	 * @return true if the mission can end, false if it needs to continue
	 */
	public boolean canEnd();
	
	/**
	 * This method gets the mission name
	 * 
	 * @return A String representing the name of this mission
	 */
	public String getMissionName();
	
	/**
	 * This method gets the current task name
	 * 
	 * @return A String representing the name of the current task
	 */
	public String getCurrentTaskName();
	
	/**
	 * This method gets the message that should be displayed on the end
	 * of this mission
	 * 
	 * @return A String representing the end message
	 */
	public String getEndMessage();
	
	/**
	 * Generates the goals for this mission
	 * 
	 * @return A GoalList object containing the goals for this mission, or null
	 * if there are no goals for this mission
	 */
	public GoalList getGoals();
	
	/**
	 * Generates the mission-specific paint Strings for this mission
	 * This is combined with the VikingScript specific paint Strings
	 * seamlessly into one paint
	 * 
	 * @return An array of Strings representing the mission specific paint info,
	 * or null if there is none
	 */
	public String[] getMissionPaint();
	
	/**
	 * This is the main method for the mission. Essentially the same as onLoop(),
	 * but for a mission
	 * 
	 * @return the sleep time for this cycle
	 */
	public int execute();
	
	/**
	 * Resets the mission specific paint info
	 */
	public void resetPaint();
	
}
