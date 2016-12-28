package viking.framework.task;

import viking.framework.VMethodProvider;
import viking.framework.mission.Mission;

/**
 * Created by Sphiinx on 4/20/2016.
 */
public abstract class Task<T extends Mission> extends VMethodProvider {
	
	protected T mission;
	
	public Task(T mission)
	{
		this.mission = mission;
		exchangeContext(mission.getScript());
	}
	
    abstract boolean validate();

    abstract void execute();

}