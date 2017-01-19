package viking.framework.goal.impl;

import org.osbot.rs07.api.Combat;

import viking.framework.goal.Goal;

public class CombatGoal implements Goal
{
	private Combat api;
	private int goalLvl;
	
	public CombatGoal(Combat api, int goal)
	{
		this.api = api;
		this.goalLvl = goal;
	}
	
	@Override
	public String getName()
	{
		return "Combat Goal";
	}

	@Override
	public boolean hasReached()
	{
		return api.getCombatLevel() >= goalLvl;
	}

	@Override
	public String getCompletionMessage()
	{
		return "Combat goal of " + goalLvl + " has been reached";
	}

}
