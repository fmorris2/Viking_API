package viking.api.skills.fishing.enums;

import org.osbot.rs07.api.map.Area;

/**
 * Created by Sphiinx on 1/3/2017.
 */
public enum FishingLocation {

    LUMBRIDGE(new Area(new int[][]{
                    { 3250, 3165 },
                    { 3243, 3139 },
                    { 3233, 3138 },
                    { 3243, 3162 }
            }), false, FishType.SHRIMP);

    private final Area AREA;
    private final boolean MEMBERS;
    private final FishType FISH_TYPE[];

    FishingLocation(Area area, boolean members, FishType... tree_type) {
        this.AREA = area;
        this.MEMBERS = members;
        this.FISH_TYPE = tree_type;
    }

    public Area getArea() {
        return AREA;
    }

    public boolean isMembers() {
        return MEMBERS;
    }

    public FishType[] getFishTypes() {
        return FISH_TYPE;
    }

}
