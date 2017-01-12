package viking.api.skills.mining.enums;

/**
 * Created by Sphiinx on 12/27/2016.
 */
public enum OreType {

    RUNE_ESSENCE(1436),
    CLAY(434),
    COPPER_ORE(436),
    TIN_ORE(438),
    LIMESTONE(-1),
    IRON_ORE(440),
    SILVER_ORE(442),
    COAL(453),
    PURE_ESSENCE(-1),
    GOLD(444),
    MITHRIL(447),
    ADAMANTITE(449),
    RUNITE(451);

    private final int ITEM_ID;

    OreType(int item_id) {
        this.ITEM_ID = item_id;
    }

    public int getItemID() {
        return ITEM_ID;
    }

}