package viking.api.skills.woodcutting.enums;

/**
 * Created by Sphiinx on 12/27/2016.
 */
public enum TreeType {

    NORMAL("Tree"),
    OAK("Oak"),
    WILLOW("Willow"),
    TEAK("Teak"),
    MAPLE("Maple"),
    MAHOGANY("Mahogany"),
    YEW("Yew"),
    MAGIC("Magic"),
    REDWOOD("Redwood");

    private final String TREE_NAME;

    TreeType(String tree_name) {
        this.TREE_NAME = tree_name;
    }

    public String getTreeName() {
        return TREE_NAME;
    }

}

