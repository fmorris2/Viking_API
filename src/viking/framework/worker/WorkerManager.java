package viking.framework.worker;

import viking.framework.mission.Mission;

/**
 * This is the class that will be used by the Mission in order to 
 * execute / drive its logic
 * 
 * @author The Viking
 *
 * @param <T> the mission that this manager is driving
 */
public abstract class WorkerManager<T extends Mission>
{
	protected T mission;
	protected Worker<T> current;
	
	public WorkerManager(T mission)
	{
		this.mission = mission;
		current = decide();
	}
	
	/**
	 * This method handles the main control flow for the
	 * mission, and it tells us what we need to do next
	 * 
	 * @return the worker that should currently be executing
	 */
	public abstract Worker<T> decide();
	
	/**
	 * This is the method that drives the workers,
	 * it doesn't actually make any decisions on which
	 * worker needs to execute. That is handled by the
	 * implementation of the manager
	 * 
	 * If the current worker is null, this method will fail.
	 * This is due to the fact that scripts should never be on
	 * an unknown state
	 */
	public void work()
	{
		assert current != null; //Current worker should NEVER be null
		
		if(current == null)
		{
			mission.getScript().log(this, "assert not working");
			throw new NullPointerException();
		}
		
		current = current.needsRepeat() ? current : decide();
		current.work();
	}
}
