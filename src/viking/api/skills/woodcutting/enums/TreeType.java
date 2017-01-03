package viking.api.skills.woodcutting.enums;

/**
 * Created by Sphiinx on 12/27/2016.
 */
public enum TreeType {

    NORMAL("Tree", 1),
    EVERGREEN("Evergreen", 1),
    OAK("Oak", 15),
    WILLOW("Willow", 30),
    TEAK("Teak", 35),
    MAPLE("Maple", 45),
    MAHOGANY("Mahogany", 50),
    YEW("Yew", 60),
    MAGIC("Magic", 75),
    REDWOOD("Redwood", 90);

    private final String TREE_NAME;
    private final int LEVEL;

    TreeType(String tree_name, int level) {
        this.TREE_NAME = tree_name;
        this.LEVEL = level;
    }

    public String getTreeName() {
        return TREE_NAME;
    }

    public int getWoodcuttingLevel() {
        return LEVEL;
    }

}

