package viking.framework.antiban.reaction;

import viking.api.Timing;

public class ReactionEntry
{
	private long reactionStart, reactionEnd;
	private boolean hasStarted;
	
	public void startDoing()
	{
		hasStarted = true;
	}
	
	public void stopDoing()
	{
		reactionStart = Timing.currentMs();
	}
	
	public void end()
	{
		reactionEnd = Timing.currentMs();
		hasStarted = false;
	}
	
	public boolean hasStarted()
	{
		return hasStarted;
	}
	
	public long getLength()
	{
		return reactionEnd - reactionStart;
	}
}
