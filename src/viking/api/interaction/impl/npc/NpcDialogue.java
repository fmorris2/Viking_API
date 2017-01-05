package viking.api.interaction.impl.npc;

import org.osbot.rs07.api.EntityAPI;
import org.osbot.rs07.api.filter.ActionFilter;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.filter.NameFilter;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.MethodProvider;

import viking.api.Timing;
import viking.api.filter.VFilters;
import viking.api.interaction.EntityInteraction;
import viking.framework.VMethodProvider;

public class NpcDialogue extends EntityInteraction<NPC>
{	
	private static final int CHAT_MASTER = 231, NAME_CHILD = 1, CONTINUE_CHILD = 2, MESSAGE_CHILD = 3;
	
	private int[] options;
	private int optionIndex;
	
	public NpcDialogue(VMethodProvider vmp, String action, String name, int searchDistance, int... options)
	{
		super(vmp, action, name, searchDistance);
		this.options = options;
	}

	@Override
	protected boolean interact()
	{
		if(!inDialogue())
		{
			vmp.log("Not in dialogue.... attempting to talk to npc");
			if(target.interact(action))
				Timing.waitCondition(() -> inDialogue(), 8000);
		}
		
		while(inDialogue())
		{
			vmp.log("in dialogue...");
			if(vmp.dialogues.isPendingContinuation())
				vmp.dialogues.clickContinue();
			else if(vmp.dialogues.isPendingOption())
			{
				if(optionIndex >= options.length)
					break;
				
				vmp.dialogues.selectOption(options[optionIndex]);
				optionIndex++;
			}
			
			vmp.waitMs(MethodProvider.random(300, 900));
		}
		
		return !inDialogue();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Filter<Entity> findFilter()
	{
		return VFilters.and(new NameFilter<NPC>(name), new ActionFilter<NPC>(action));
	}

	@Override
	protected EntityAPI<NPC> getAPI()
	{
		return vmp.npcs;
	}
	
	private boolean inDialogue()
	{
		return vmp.widgets.isVisible(CHAT_MASTER);
	}

}