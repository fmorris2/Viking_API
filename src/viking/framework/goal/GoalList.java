package viking.framework.goal;

import java.util.ArrayList;

import viking.framework.goal.impl.ResourceGoal;
import viking.framework.goal.impl.TimeGoal;

/**
 * This is an extension of the ArrayList class, which provides
 * additional utilities specific to the goal system
 * 
 * @author The Viking
 *
 */
public class GoalList extends ArrayList<Goal>
{
	private static final long serialVersionUID = -6247167564246021589L;
	
	/**
	 * Constructor for GoalList, which conveniently accepts an
	 * array of goals, and adds them all to the list
	 * 
	 * @param goals Goals to add to this list
	 */
	public GoalList(Goal... goals)
	{
		for(Goal g : goals)
			add(g);
	}
	
	/**
	 * This method is called whenever the inventory changes (specifically when it is added to).
	 * The function of this method is to update any resource goals we have in this list
	 * 
	 * @param id The item id that has been added to the inventory
	 * @param count The count of the item that has been added to the inventory
	 */
	public void updateResourceGoals(int id, int count)
	{
		for(Goal g : this)
			if(g instanceof ResourceGoal && ((ResourceGoal)g).containsId(id))
				((ResourceGoal)g).update(count);	
	}
	
	/**
	 * This method simply tells us whether or not we have reached all the goals
	 * in our goal list.
	 * 
	 * @return true if we've reached all the goals in the list, false if not
	 */
	public boolean hasReachedGoals()
	{	
		for(int i = 0; i < size(); i++)
		{
			if(get(i).hasReached())
			{
				System.out.println(get(i).getCompletionMessage());
				remove(i);
			}
		}	
		return isEmpty();
	}
	
	/**
	 * Resets any time goals in this list
	 */
	public void resetTimeGoals()
	{
		for(Goal g : this)
			if(g instanceof TimeGoal)
				((TimeGoal)g).reset();
	}
	
	/**
	 * This method returns a string containing only the names
	 * of the goals in this list, no other details about them
	 * 
	 * @return a String, containing only the names of the goals in this list
	 */
	public String getRunningGoals()
	{
		String str = "";
		for(Goal g : this)
			str += "["+g.getName()+"]";
		
		return str;
	}
	
	/**
	 * Converts this object into a String, with all of the goals
	 * and their respective details
	 * 
	 * @return a String, containing the goals and their details
	 */
	public String toString()
	{
		String str = "";
		for(Goal g : this)
			str += "["+g+"]";
		
		return str;
	}
}
