package viking.framework.antiban.reaction;

import viking.framework.antiban.reaction.events.impl.TreeEvent;

public enum ReactionEvents
{
	NORMAL_TREE(new TreeEvent("Tree")),
	OAK_TREE(new TreeEvent("Oak")),
	WILLOW_TREE(new TreeEvent("Willow")),
	YEW_TREE(new TreeEvent("Yew"));
	
	public final ReactionEvent EVENT;
	
	ReactionEvents(ReactionEvent e)
	{
		EVENT = e;
	}
	
	public static int getReactionTime(int id)
	{
		Integer i = new Integer(id);
		for(ReactionEvents e : values())
			if(e.EVENT.isValid(i))
				return e.EVENT.getReactionTime();
		
		return 0;
	}
	
	public static int getReactionTime(String name)
	{
		for(ReactionEvents e : values())
			if(e.EVENT.isValid(name))
				return e.EVENT.getReactionTime();
		
		return 0;
	}
}
