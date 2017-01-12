package viking.api.interaction;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

import viking.api.interaction.impl.npc.ClickNpc;
import viking.api.interaction.impl.npc.NpcDialogue;
import viking.api.interaction.impl.object.ClickObject;
import viking.api.interaction.impl.object.ItemOnObject;
import viking.framework.VMethodProvider;

public class VInteractionFactory extends VMethodProvider
{
	//Item on object
	public ItemOnObject itemOnObj(String item, String object, int searchDistance)
	{
		return new ItemOnObject(this, item, object, searchDistance);
	}
	
	public ItemOnObject itemOnObj(String item, String object, int searchDistance, Position fallBack)
	{
		ItemOnObject itemOnObj = new ItemOnObject(this, item, object, searchDistance);
		itemOnObj.setFallBackPos(fallBack);
		return itemOnObj;
	}
	
	//ClickObject
	public ClickObject clickObject(String action, String name, int searchDistance)
	{
		return new ClickObject(this, action, name, searchDistance);
	}
	
	public ClickObject clickObject(String action, String name, int searchDistance, Position fallBack)
	{
		ClickObject clickObj = new ClickObject(this, action, name, searchDistance);
		clickObj.setFallBackPos(fallBack);
		return clickObj;
	}
	
	public ClickObject clickObject(String action, RS2Object target)
	{
		return new ClickObject(this, action, target);
	}
	
	public ClickObject clickObject(String action, RS2Object target, Position fallBack)
	{
		ClickObject clickObj = new ClickObject(this, action, target);
		clickObj.setFallBackPos(fallBack);
		return clickObj;
	}
	
	public ClickObject clickObject(String action, String name, Area a)
	{
		return new ClickObject(this, action, name, a);
	}
	
	public ClickObject clickObject(String action, String name, Area a, Position fallBack)
	{
		ClickObject clickObj = new ClickObject(this, action, name, a);
		clickObj.setFallBackPos(fallBack);
		return clickObj;
	}
	
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
