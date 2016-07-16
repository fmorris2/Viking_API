package viking.framework.mission;

import java.util.Queue;

import viking.api.ScriptUtils;

/**
 * This class will handle the execution / flow of missions for any
 * Viking script.
 * 
 * @author The Viking
 */
public class MissionHandler
{
	private Queue<Mission> missions;
	
	/**
	 * Primary constructor for MissionHandler. Takes in a queue of missions
	 * to initially add to the mission queue
	 * 
	 * @param missions the initial queue of missions to add
	 */
	public MissionHandler(Queue<Mission> missions)
	{
		this.missions = missions;
	}
	
	/**
	 * Handles the main mission execution / driving
	 * 
	 * @return the sleep time for this loop cycle
	 */
	public int execute()
	{
		//if mission queue is null or empty, end the script
		if(missions == null || missions.isEmpty())
		{
			ScriptUtils.log(this, "No more missions... Ending");
			return -1;
		}
		
		Mission current = missions.peek();
		
		//if the current mission is over, move on to the next one
		if(current.canEnd())
		{
			ScriptUtils.log(this, current.getMissionName() + " can end... moving on to next mission");
			ScriptUtils.log(this, current.getEndMessage());
			missions.poll();
			return 10;
		}
		else //execute the current mission
			return current.execute();
	}

	//Getters & Setters
	public Queue<Mission> getMissions()
	{
		return missions;
	}
}
