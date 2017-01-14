package viking.api.skills.fishing.enums;

import java.util.Arrays;

/**
 * Created by Sphiinx on 1/3/2017.
 */
public enum FishingEquipment {

    FISHING_ROD(307, 5, 1),
    FLY_FISHING_ROD(309, 20, 1),
    SMALL_FISHING_NET(303, 1, 1),
    BIG_FISHING_NET(-1, -1, 1),
    HARPOON(311, 35, 1),
    LOBSTER_POT(301, 40, 1),
    FEATHER(314, 20, -1),
    FISHING_BAIT(313, 5, -1);

    private final int ITEM_ID;
    private final int FISHING_LEVEL;
    private final int AMOUNT;

    FishingEquipment(int item_id, int fishing_level, int amount) {
        this.ITEM_ID = item_id;
        this.FISHING_LEVEL = fishing_level;
        this.AMOUNT = amount;
    }

    public int getItemID() {
        return ITEM_ID;
    }

    public int getFishingLevel() {
        return this.FISHING_LEVEL;
    }

    public int getItemAmount() {
        return AMOUNT;
    }

    public static int[] getItemIDs() {
        return Arrays.stream(FishingEquipment.values()).mapToInt(FishingEquipment::getItemID).toArray();
    }

}

