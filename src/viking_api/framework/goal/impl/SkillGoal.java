package viking_api.framework.goal.impl;

import org.osbot.rs07.api.Skills;
import org.osbot.rs07.api.ui.Skill;

import viking_api.framework.goal.Goal;

/**
 * This class represents a skill-based goal, where we are trying to achieve
 * a specific level in a specific skill.
 * 
 * @author The Viking
 *
 */
public class SkillGoal implements Goal
{	
	private transient Skills api;
	private transient Skill skill;
	private int skillIndex;
	private int goal;
	
	public SkillGoal(Skills api, Skill skill, int goal)
	{
		this.api = api;
		this.skill = skill;
		this.skillIndex = skill.ordinal();
		this.goal = goal;
	}

	@Override
	public boolean hasReached()
	{
		return api.getStatic(skill) >= goal;
	}

	@Override
	public String getCompletionMessage()
	{
		return "Goal Complete: Achieve " + goal + " " + getSkill().name().toLowerCase();
	}

	@Override
	public String getName()
	{
		return goal + " " + getSkill().name().toLowerCase() + " (" + api.getStatic(skill) + ")";
	}
	
	public String toString()
	{
		return goal + " " + getSkill().name().toLowerCase();
	}
	
	public Skill getSkill()
	{
		if(skill == null)
			skill = Skill.values()[skillIndex];
		
		return skill;
	}
	
	public int getGoal()
	{
		return goal;
	}

}
