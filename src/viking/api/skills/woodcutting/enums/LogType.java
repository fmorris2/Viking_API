package viking.api.skills.woodcutting.enums;

/**
 * Created by Sphiinx on 12/27/2016.
 */
public enum LogType {

    NORMAL(1511),
    OAK(1521),
    WILLOW(1519),
    TEAK(6333),
    MAPLE(1517),
    MAHOGANY(6332),
    YEW(1515),
    MAGIC(1513),
    REDWOOD(19669);

    private final int ITEM_ID;

    LogType(int item_id) {
        this.ITEM_ID = item_id;
    }

    public int getItemID() {
        return ITEM_ID;
    }

}