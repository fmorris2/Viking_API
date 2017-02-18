package viking.framework.antiban.reaction;

import viking.api.Timing;

public class ReactionEntry
{
	private long reactionStart, reactionEnd;
	private boolean hasStartedEvent, hasStartedReaction;
	
	public void startDoing()
	{
		hasStartedEvent = true;
		hasStartedReaction = false;
	}
	
	public void stopDoing()
	{
		reactionStart = Timing.currentMs();
		hasStartedReaction = true;
	}
	
	public void end()
	{
		reactionEnd = Timing.currentMs();
		hasStartedReaction = false;
		hasStartedEvent = false;
	}
	
	public boolean hasStartedEvent()
	{
		return hasStartedEvent;
	}
	
	public boolean hasStartedReaction()
	{
		return hasStartedReaction;
	}
	
	public long getLength()
	{
		return reactionEnd - reactionStart;
	}
}
