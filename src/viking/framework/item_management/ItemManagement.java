package viking.framework.item_management;

import viking.api.skills.fishing.enums.FishType;
import viking.api.skills.mining.enums.RockType;
import viking.api.skills.woodcutting.enums.LogType;

public interface ItemManagement {

    int[] ORION_SELL_ITEMS = {
            LogType.NORMAL.getItemID(), LogType.OAK.getItemID(), LogType.WILLOW.getItemID(),
            FishType.SHRIMP.getItemID(), FishType.SARDINE.getItemID(), FishType.HERRING.getItemID(),
            FishType.ANCHOVIES.getItemID(), FishType.TROUT.getItemID(), FishType.PIKE.getItemID(),
            FishType.SALMON.getItemID(), RockType.RUNE_ESSENCE.getItemID(), RockType.CLAY.getItemID(),
            RockType.COPPER_ORE.getItemID(), RockType.TIN_ORE.getItemID(), RockType.IRON_ORE.getItemID(),
            526, 1739, 314
    };
    //bones, cowhide, feather

    public IMEntry[] itemsToBuy();

    public int[] itemsToSell();
}
