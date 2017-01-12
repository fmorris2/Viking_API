package viking.api.skills.mining.enums;

/**
 * Created by Sphiinx on 12/27/2016.
 */
public enum RockType {

    RUNE_ESSENCE(-1, false, 1),
    CLAY(6705, false, 1),
    COPPER_ORE(4645, false, 1),
    TIN_ORE(53, false, 1),
    LIMESTONE(10295, true, 10),
    IRON_ORE(2576, false, 15),
    SILVER_ORE(74, false, 20),
    COAL(10508, false, 30),
    PURE_ESSENCE(-1, true, 30),
    GOLD(8885, false, 40),
    MITHRIL(-22239, false, 55),
    ADAMANTITE(21662, false, 70),
    RUNITE(-1, false, 85);

    private final int COLOR;
    private final boolean IS_MEMBERS;
    private final int LEVEL;

    RockType(int color, boolean is_members, int level) {
        this.COLOR = color;
        this.IS_MEMBERS = is_members;
        this.LEVEL = level;
    }

    public short getRockColor() {
        return (short) COLOR;
    }

    public boolean isMembers() {
        return IS_MEMBERS;
    }

    public int getMiningLevel() {
        return LEVEL;
    }

}

