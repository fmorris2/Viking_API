package viking.api.skills.fishing.enums;

import java.util.Arrays;

/**
 * Created by Sphiinx on 1/3/2017.
 */
public enum FishingEquipment {

    FISHING_ROD(307, 1),
    FLY_FISHING_ROD(309, 1),
    SMALL_FISHING_NET(303, 1),
    BIG_FISHING_NET(-1, 1),
    HARPOON(311, 1),
    LOBSTER_POT(301, 1),
    FEATHER(314, 0),
    FISHING_BAIT(313, 0);

    private final int ITEM_ID;
    private final int AMOUNT;

    FishingEquipment(int item_id, int amount) {
        this.ITEM_ID = item_id;
        this.AMOUNT = amount;
    }

    public int getItemID() {
        return ITEM_ID;
    }

    public int getItemAmount() {
        return AMOUNT;
    }

    public static int[] getItemIDs() {
        return Arrays.stream(FishingEquipment.values()).mapToInt(FishingEquipment::getItemID).toArray();
    }

}

