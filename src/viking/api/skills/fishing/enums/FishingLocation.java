package viking.api.skills.fishing.enums;

import org.osbot.rs07.api.map.Area;

/**
 * Created by Sphiinx on 1/3/2017.
 */
public enum FishingLocation {

    F_LUMBRIDGE_SOUTH_1(new Area(new int[][]{
            {3250, 3165},
            {3243, 3139},
            {3233, 3138},
            {3243, 3162}
    }), false, false, -1, FishType.SHRIMP, FishType.SARDINE, FishType.HERRING, FishType.ANCHOVIES),
    F_AL_KHARID_1(new Area(new int[][]{
            {3254, 3151},
            {3264, 3158},
            {3287, 3137},
            {3277, 3131}
    }), false, false, 29, FishType.SHRIMP, FishType.SARDINE, FishType.HERRING, FishType.ANCHOVIES),
    F_LUMBRIDGE_NORTH_1(new Area(new int[][]{
            {3237, 3227},
            {3249, 3227},
            {3242, 3259},
            {3231, 3259}
    }), false, false, -1, FishType.PIKE, FishType.SALMON, FishType.TROUT),
    F_BARBARIAN_VILLAGE_1(new Area(new int[][]{
            {3101, 3440},
            {3118, 3439},
            {3116, 3412},
            {3100, 3413}
    }), false, false, -1, FishType.PIKE, FishType.SALMON, FishType.TROUT),
    F_DRAYNOR_VILLAGE_1(new Area(new int[][]{
            {3072, 3237},
            {3077, 3243},
            {3094, 3228},
            {3085, 3219}
    }), false, false, 15, FishType.SHRIMP, FishType.SARDINE, FishType.HERRING, FishType.ANCHOVIES),
    F_KARAMJA_1(new Area(new int[][]{
            {2916, 3192},
            {2938, 3190},
            {2936, 3165},
            {2909, 3177}
    }), false, true, -1, FishType.SHRIMP, FishType.SARDINE, FishType.HERRING, FishType.ANCHOVIES, FishType.TUNA, FishType.LOBSTER, FishType.SWORDFISH),
    F_MUDSKIPPER_POINT_1(new Area(new int[][]{
            {2976, 3180},
            {2991, 3186},
            {3004, 3158},
            {2991, 3148}
    }), false, true, -1, FishType.SHRIMP, FishType.SARDINE, FishType.HERRING, FishType.ANCHOVIES);

    private final Area AREA;
    private final boolean MEMBERS;
    private final boolean SHOULD_USE_DEPOSIT_BOX;
    private final int RECOMMENDED_COMBAT_LEVEL;
    private final FishType FISH_TYPE[];

    FishingLocation(Area area, boolean members, boolean use_deposit_box, int recommended_combat_level, FishType... tree_type) {
        this.AREA = area;
        this.MEMBERS = members;
        this.SHOULD_USE_DEPOSIT_BOX = use_deposit_box;
        this.RECOMMENDED_COMBAT_LEVEL = recommended_combat_level;
        this.FISH_TYPE = tree_type;
    }

    public Area getArea() {
        return AREA;
    }

    public boolean isMembers() {
        return MEMBERS;
    }

    public boolean shouldUseDepositBox() {
        return SHOULD_USE_DEPOSIT_BOX;
    }

    public int getRecommendedCombatLevel() {
        return RECOMMENDED_COMBAT_LEVEL;
    }

    public FishType[] getFishTypes() {
        return FISH_TYPE;
    }

}
