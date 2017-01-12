package viking.api.interaction.impl.object;

import org.osbot.rs07.api.EntityAPI;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.filter.NameFilter;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Tab;

import viking.api.interaction.EntityInteraction;
import viking.framework.VMethodProvider;

public class ItemOnObject extends EntityInteraction<RS2Object>
{	
	public ItemOnObject(VMethodProvider vmp, String item, String object, int searchDistance)
	{
		super(vmp, item, object, searchDistance);
	}

	@Override
	protected boolean interact()
	{
		if(!vmp.tabs.open(Tab.INVENTORY))
			return false;
		
		Item item = vmp.inventory.getItem(action);
		if(item == null)
			return false;
		
		String selectedItem = vmp.inventory.getSelectedItemName();
		boolean isItemSelected = selectedItem != null && selectedItem.equals(action);
		
		if(isItemSelected)
			return target.interact("Use");
			
		if(selectedItem == null || (!isItemSelected && vmp.inventory.deselectItem()))
		{
			if(item.interact("Use") && target.interact("Use"))
				return true;
		}
		
		return false;
	}

	@Override
	protected Filter<Entity> findFilter()
	{
		return new NameFilter<Entity>(name);
	}

	@Override
	protected EntityAPI<RS2Object> getAPI()
	{
		return vmp.objects;
	}
	
}
