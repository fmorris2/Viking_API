package viking.framework.goal.impl;

import viking.framework.goal.Goal;

/**
 * This class represents a goal that will never end
 * 
 * @author The Viking
 */
public class InfiniteGoal implements Goal
{
	@Override
	public boolean hasReached()
	{
		return false;
	}

	@Override
	public String getCompletionMessage()
	{
		return "This will never be reached";
	}

	@Override
	public String getName()
	{
		return "Infinite";
	}
	
	public String toString()
	{
		return getName();
	}
}
