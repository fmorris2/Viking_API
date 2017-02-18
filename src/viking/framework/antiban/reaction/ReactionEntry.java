package viking.framework.antiban.reaction;

import viking.api.Timing;

public class ReactionEntry
{
	private long reactionStart, reactionEnd;
	
	public void start()
	{
		reactionStart = Timing.currentMs();
	}
	
	public void end()
	{
		reactionEnd = Timing.currentMs();
	}
	
	public long getLength()
	{
		return reactionEnd - reactionStart;
	}
}
