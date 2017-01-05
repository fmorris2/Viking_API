package viking.api.depositbox.enums;

import org.osbot.rs07.api.map.Area;

/**
 * Created by Sphiinx on 1/4/2017.
 */
public enum  DepositBoxLocation {

    PORT_SARIM_DEPOSIT_BOX(new Area(new int[][]{
            {3041, 3237},
            {3048, 3237},
            {3048, 3229},
            {3041, 3230}
    }), false);

    private final Area AREA;
    private final boolean IS_MEMEBERS;

    DepositBoxLocation(Area area, boolean is_members) {
        this.AREA = area;
        this.IS_MEMEBERS = is_members;
    }

    public Area getArea() {
        return AREA;
    }

    public boolean isMembers() {
        return IS_MEMEBERS;
    }

}

