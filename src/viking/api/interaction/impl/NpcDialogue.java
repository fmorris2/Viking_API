package viking.api.interaction.impl;

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
			if(target.interact(action))
				Timing.waitCondition(() -> inDialogue(), 8000);
		}
		
		while(inDialogue())
		{
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
		return vmp.dialogues.isPendingContinuation() || vmp.dialogues.isPendingOption();
	}

}