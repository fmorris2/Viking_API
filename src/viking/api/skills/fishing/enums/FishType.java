package viking.api.skills.fishing.enums;

import java.util.Arrays;

/**
 * Created by Sphiinx on 1/3/2017.
 */
public enum FishType {

    SHRIMP(317, 1, false, "Net", new FishingEquipment[]{
            FishingEquipment.SMALL_FISHING_NET
    }, FishingLocation.F_LUMBRIDGE_SOUTH_1, FishingLocation.F_AL_KHARID_1, FishingLocation.F_KARAMJA_1, FishingLocation.F_MUDSKIPPER_POINT_1, FishingLocation.F_DRAYNOR_VILLAGE_1),
    SARDINE(327, 5, false, "Bait", new FishingEquipment[]{
            FishingEquipment.FISHING_BAIT,
            FishingEquipment.FISHING_ROD
    }, FishingLocation.F_LUMBRIDGE_SOUTH_1, FishingLocation.F_AL_KHARID_1, FishingLocation.F_KARAMJA_1, FishingLocation.F_MUDSKIPPER_POINT_1, FishingLocation.F_DRAYNOR_VILLAGE_1),
    HERRING(345, 10, false, "Bait", new FishingEquipment[]{
            FishingEquipment.FISHING_BAIT,
            FishingEquipment.FISHING_ROD
    }, FishingLocation.F_LUMBRIDGE_SOUTH_1, FishingLocation.F_AL_KHARID_1, FishingLocation.F_KARAMJA_1, FishingLocation.F_MUDSKIPPER_POINT_1, FishingLocation.F_DRAYNOR_VILLAGE_1),
    ANCHOVIES(321, 15, false, "Net", new FishingEquipment[]{
            FishingEquipment.SMALL_FISHING_NET
    }, FishingLocation.F_LUMBRIDGE_SOUTH_1, FishingLocation.F_AL_KHARID_1, FishingLocation.F_KARAMJA_1, FishingLocation.F_MUDSKIPPER_POINT_1, FishingLocation.F_DRAYNOR_VILLAGE_1),
    TROUT(335, 20, false, "Lure", new FishingEquipment[]{
            FishingEquipment.FLY_FISHING_ROD,
            FishingEquipment.FEATHER,
    }, FishingLocation.F_LUMBRIDGE_NORTH_1, FishingLocation.F_BARBARIAN_VILLAGE_1),
    PIKE(349, 25, false, "Bait", new FishingEquipment[]{
            FishingEquipment.FISHING_ROD,
            FishingEquipment.FISHING_BAIT,
    }, FishingLocation.F_LUMBRIDGE_NORTH_1, FishingLocation.F_BARBARIAN_VILLAGE_1),
    SALMON(331, 30, false, "Lure", new FishingEquipment[]{
            FishingEquipment.FLY_FISHING_ROD,
            FishingEquipment.FEATHER,
    }, FishingLocation.F_LUMBRIDGE_NORTH_1, FishingLocation.F_BARBARIAN_VILLAGE_1),
    TUNA(359, 35, false, "Harpoon", new FishingEquipment[]{
            FishingEquipment.HARPOON,
    }, FishingLocation.F_KARAMJA_1),
    LOBSTER(377, 40, false, "Cage", new FishingEquipment[]{
            FishingEquipment.LOBSTER_POT
    }, FishingLocation.F_KARAMJA_1),
    SWORDFISH(371, 50, false, "Harpoon", new FishingEquipment[]{
            FishingEquipment.HARPOON,
    }, FishingLocation.F_KARAMJA_1);


    public int ITEM_ID;
    public int FISHING_LEVEL;
    public boolean IS_MEMBERS;
    public String FISH_ACTION;
    public FishingEquipment[] REQUIRED_EQUIPMENT;
    public FishingLocation[] FISHING_LOCATIONS;

    FishType(int item_id, int fishing_level, boolean is_members, String fish_action, FishingEquipment[] required_equipment, FishingLocation... fishing_locations) {
        this.ITEM_ID = item_id;
        this.FISHING_LEVEL = fishing_level;
        this.IS_MEMBERS = is_members;
        this.FISH_ACTION = fish_action;
        this.REQUIRED_EQUIPMENT = required_equipment;
        this.FISHING_LOCATIONS = fishing_locations;
    }

    public int getItemID() {
        return ITEM_ID;
    }

    public int getFishingLevel() {
        return FISHING_LEVEL;
    }

    public boolean isMembers() {
        return this.IS_MEMBERS;
    }

    public String getFishingAction() {
        return FISH_ACTION;
    }

    public FishingEquipment[] getRequiredEquipment() {
        return REQUIRED_EQUIPMENT;
    }

    public FishingLocation[] getFishingLocations() {
        return FISHING_LOCATIONS;
    }

    public int[] getRequiredEquipmentIDs() {
        return Arrays.stream(REQUIRED_EQUIPMENT).mapToInt(FishingEquipment::getItemID).toArray();
    }

    /**
     * Gets all of the fish item ID's in the enum.
     *
     * @return An integer array containing all of the fish item ID's in the enum.
     * */
    public static int[] getItemIDs() {
        return Arrays.stream(FishType.values()).mapToInt(FishType::getItemID).toArray();
    }

}
