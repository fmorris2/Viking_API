package viking.framework.goal.impl;


import viking.api.Timing;
import viking.framework.goal.Goal;

/**
 * This class represents a time-based goal, where we are aiming
 * to run the script for a specific amount of time
 * 
 * @author The Viking
 *
 */
public class TimeGoal implements Goal
{
	private long startTime;
	private long timeAmount;
	
	public TimeGoal(long timeAmount)
	{
		this.startTime = Timing.currentMs();
		this.timeAmount = timeAmount;
	}

	@Override
	public boolean hasReached()
	{
		return Timing.timeFromMark(startTime) >= timeAmount;
	}

	@Override
	public String getCompletionMessage()
	{
		return "Goal Complete: Execute mission for " + Timing.msToString(timeAmount);
	}

	@Override
	public String getName()
	{
		return "Time goal: " + Timing.msToString(Timing.timeFromMark(startTime + timeAmount));
	}
	
	public String toString()
	{
		return "Time goal: " + Timing.msToString(timeAmount);
	}
	
	public void reset()
	{
		startTime = Timing.currentMs();
	}

}
