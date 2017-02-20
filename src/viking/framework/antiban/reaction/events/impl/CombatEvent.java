package viking.framework.antiban.reaction.events.impl;

import viking.framework.VMethodProvider;
import viking.framework.antiban.reaction.ReactionEvent;

public class CombatEvent extends ReactionEvent
{

	public CombatEvent(VMethodProvider api, String entityName)
	{
		super(api, entityName);
	}
	
	public CombatEvent(String entityName)
	{
		super(null, entityName);
	}

	@Override
	public boolean isDoing()
	{
		return api.combat.isFighting() || api.myPlayer().getInteracting() != null;
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
