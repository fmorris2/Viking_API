package viking.framework.task;

import viking.framework.mission.Mission;

/**
 * Created by Sphiinx on 4/20/2016.
 */
public abstract class Task<T extends Mission> {
	
	protected T mission;
	
	public Task(T mission)
	{
		this.mission = mission;
	}
	
    abstract boolean validate();

    abstract void execute();

}