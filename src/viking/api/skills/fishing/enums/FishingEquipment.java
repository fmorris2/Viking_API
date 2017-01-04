package viking.api.skills.fishing.enums;

/**
 * Created by Sphiinx on 1/3/2017.
 */
public enum FishingEquipment {

    FISHING_ROD(307),
    FLY_FISHING_ROD(309),
    SMALL_FISHING_NET(303),
    BIG_FISHING_NET(-1),
    HARPOON(311),
    FEATHER(314),
    FISHING_BAIT(313);

    private final int ITEM_ID;

    FishingEquipment(int item_id) {
        this.ITEM_ID = item_id;
    }

    public int getItemID() {
        return ITEM_ID;
    }

}

