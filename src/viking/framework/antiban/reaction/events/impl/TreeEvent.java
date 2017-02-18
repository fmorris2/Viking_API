package viking.framework.antiban.reaction.events.impl;

import viking.framework.VMethodProvider;
import viking.framework.antiban.reaction.ReactionEvent;

public class TreeEvent extends ReactionEvent
{
	public TreeEvent(VMethodProvider api, String treeName)
	{
		super(api, treeName);
	}

	@Override
	public boolean isDoing()
	{
		return api.myPlayer().getAnimation() != -1 && !api.myPlayer().isMoving();
	}

	@Override
	protected int getEntityID()
	{
		return 0;
	}

	@Override
	protected String getEntityName()
	{
		return null;
	}

}
