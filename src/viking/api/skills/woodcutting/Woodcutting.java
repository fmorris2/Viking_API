package viking.api.skills.woodcutting;

import org.osbot.rs07.api.ui.Skill;

import viking.api.skills.woodcutting.enums.AxeType;
import viking.framework.VMethodProvider;

/**
 * Created by Sphiinx on 12/27/2016.
 */
public class Woodcutting extends VMethodProvider {

    /**
     * Gets the best usable axe the RSPlayer has.
     *
     * @param is_checking_bank True if we are checking the BANK for the best usable axe; false otherwise.
     * @return The best usable axe the RSPlayer has.
     */
    public AxeType getBestUsableAxe(boolean is_checking_bank) {
        final AxeType[] axes = AxeType.values();

        for (int i = axes.length - 1; i >= 0; i--) {
            AxeType axe = axes[i];
            if (axe.getWoodcuttingLevel() <= skills.getStatic(Skill.WOODCUTTING) &&
                    ((inventory.getAmount(axe.getItemID()) > 0 || equipment.getAmount(axe.getItemID()) > 0)
                            || (is_checking_bank && bank.getItem(axe.getItemID()) != null)))
                return axe;
        }

        return null;
    }

    /**
     * Gets the highest level of axe the RSPlayer can use.
     *
     * @return The highest level pf axe the RSPlayer can use.
     */
    public AxeType currentAppropriateAxe() {
        final AxeType[] axes = AxeType.values();

        for (int i = axes.length - 1; i >= 0; i--) {
            if (axes[i].getWoodcuttingLevel() <= skills.getStatic(Skill.WOODCUTTING))
                return axes[i];
        }

        return AxeType.IRON;
    }

}

