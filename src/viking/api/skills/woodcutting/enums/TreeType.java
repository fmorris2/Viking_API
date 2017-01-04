package viking.api.skills.woodcutting.enums;

/**
 * Created by Sphiinx on 12/27/2016.
 */
public enum TreeType {

    NORMAL("Tree", false, 1),
    EVERGREEN("Evergreen", false, 1),
    OAK("Oak", false, 15),
    WILLOW("Willow", false, 30),
    TEAK("Teak", true, 35),
    MAPLE("Maple", true, 45),
    MAHOGANY("Mahogany", true, 50),
    YEW("Yew", false, 60),
    MAGIC("Magic", true, 75),
    REDWOOD("Redwood", true, 90);

    private final String TREE_NAME;
    private final boolean IS_MEMBERS;
    private final int LEVEL;

    TreeType(String tree_name, boolean is_members, int level) {
        this.TREE_NAME = tree_name;
        this.IS_MEMBERS = is_members;
        this.LEVEL = level;
    }

    public String getTreeName() {
        return TREE_NAME;
    }

    public boolean isMembers() {
        return IS_MEMBERS;
    }

    public int getWoodcuttingLevel() {
        return LEVEL;
    }

}

