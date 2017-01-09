package viking.api.item;

import org.osbot.rs07.api.model.Item;

import viking.framework.VMethodProvider;

public class ItemUtils extends VMethodProvider
{
	public boolean itemOnItem(String itemOne, String itemTwo)
	{
		Item one = inventory.getItem(itemOne), two = inventory.getItem(itemTwo);
		String selectedItem = inventory.getSelectedItemName();
		
		if(selectedItem == null || (!selectedItem.equals(itemOne) && inventory.deselectItem()))
		{
			if(one.interact("Use") && two.interact())
				return true;
		}
		
		return false;
	}
	
	public boolean wield(String name)
	{
		Item i = inventory.getItem(name);
		if(i != null)
			return i.interact("Wield", "Wear");
		
		return false;
	}
}
