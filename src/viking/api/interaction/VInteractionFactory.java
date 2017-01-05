package viking.api.interaction;

import org.osbot.rs07.api.model.NPC;

import viking.api.interaction.impl.npc.ClickNpc;
import viking.api.interaction.impl.npc.NpcDialogue;
import viking.framework.VMethodProvider;

public class VInteractionFactory extends VMethodProvider
{
	//NpcDialogue
	public NpcDialogue dialogue(String action, String name, int searchDistance, int... options)
	{
		return new NpcDialogue(this, action, name, searchDistance, options);
	}
	
	//ClickNpc
	public ClickNpc clickNpc(String action, NPC target)
	{
		return new ClickNpc(this, action, target);
	}
	
	public ClickNpc clickNpc(String action, String name, int searchDistance)
	{
		return new ClickNpc(this, action, name, searchDistance);
	}
}
