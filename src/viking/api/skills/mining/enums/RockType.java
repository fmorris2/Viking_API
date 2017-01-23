package viking.api.skills.mining.enums;

/**
 * Created by Sphiinx on 12/27/2016.
 */
public enum RockType {

    RUNE_ESSENCE(-1, 1436, false, 1),
    CLAY(6705, 434, false, 1),
    COPPER_ORE(4645, 436, false, 1),
    TIN_ORE(53, 438, false, 1),
    LIMESTONE(10295, -1, true, 10),
    IRON_ORE(2576, 440, false, 15),
    SILVER_ORE(74, 442, false, 20),
    COAL(10508, 453, false, 30),
    PURE_ESSENCE(-1, -1, true, 30),
    GOLD(8885, 444, false, 40),
    MITHRIL(-22239, 447, false, 55),
    ADAMANTITE(21662, 449, false, 70),
    RUNITE(-1, 451, false, 85);

    private final int COLOR;
    private final int ITEM_ID;
    private final boolean IS_MEMBERS;
    private final int LEVEL;

    RockType(int color, int item_id, boolean is_members, int level) {
        this.COLOR = color;
        this.ITEM_ID = item_id;
        this.IS_MEMBERS = is_members;
        this.LEVEL = level;
    }

    public short getRockColor() {
        return (short) COLOR;
    }

    public int getItemID() {
        return ITEM_ID;
    }

    public boolean isMembers() {
        return IS_MEMBERS;
    }

    public int getMiningLevel() {
        return LEVEL;
    }

}

