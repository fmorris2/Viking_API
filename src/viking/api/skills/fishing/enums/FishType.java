package viking.api.skills.fishing.enums;

/**
 * Created by Sphiinx on 1/3/2017.
 */
public enum FishType {

    SHRIMP(317, "Net", new FishingEquipment[]{
            FishingEquipment.SMALL_FISHING_NET
    }, FishingLocation.LUMBRIDGE);

    public int ITEM_ID;
    public String FISH_ACTION;
    public FishingEquipment[] REQUIRED_EQUIPMENT;
    public FishingLocation[] FISHING_LOCATIONS;

    FishType(int item_id, String fish_action, FishingEquipment[] required_equipment, FishingLocation... fishing_locations) {
        this.ITEM_ID = item_id;
        this.FISH_ACTION = fish_action;
        this.REQUIRED_EQUIPMENT = required_equipment;
        this.FISHING_LOCATIONS = fishing_locations;
    }

    public int getItemID() {
        return ITEM_ID;
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

}
