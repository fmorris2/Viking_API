package viking.api.depositbox;

import org.osbot.rs07.api.map.Area;
import viking.api.depositbox.enums.DepositBoxLocation;
import viking.framework.VMethodProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sphiinx on 1/4/2017.
 */
public class DepositBox extends VMethodProvider {

    /**
     * Gets an Area Array containing all of the deposit boxes in the DepositBoxLocation enum.
     *
     * @param is_members True if we should return all deposit boxes, false if we should only return f2p.
     * @return An Area Array containing all of the deposit box areas.
     * */
    public Area[] getAllDepositBoxes(boolean is_members) {
        DepositBoxLocation[] deposit_box_locations = DepositBoxLocation.values();
        List<Area> bank_areas = new ArrayList<>();
        for (DepositBoxLocation deposit_box_location : deposit_box_locations) {
            if (deposit_box_location.isMembers() && !is_members)
                continue;

            bank_areas.add(deposit_box_location.getArea());
        }

        return bank_areas.toArray(new Area[bank_areas.size()]);
    }

}

