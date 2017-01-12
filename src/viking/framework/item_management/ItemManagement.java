package viking.framework.item_management;

import viking.api.skills.fishing.enums.FishType;
import viking.api.skills.mining.enums.OreType;
import viking.api.skills.woodcutting.enums.LogType;

public interface ItemManagement
{
	public static final int[] ORION_SELL_ITEMS = 
	{
		LogType.NORMAL.getItemID(), LogType.OAK.getItemID(), LogType.WILLOW.getItemID(),
		FishType.SHRIMP.getItemID(), FishType.SARDINE.getItemID(), FishType.HERRING.getItemID(),
		FishType.ANCHOVIES.getItemID(), FishType.TROUT.getItemID(), FishType.PIKE.getItemID(),
		FishType.SALMON.getItemID(), OreType.RUNE_ESSENCE.getItemID(), OreType.CLAY.getItemID(),
		OreType.COPPER_ORE.getItemID(), OreType.TIN_ORE.getItemID(), OreType.IRON_ORE.getItemID()
	};
	
	public IMEntry[] itemsToBuy();
	public int[] itemsToSell();
}
