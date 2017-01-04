package viking.api.skills.woodcutting;

import org.osbot.rs07.api.ui.Skill;

import viking.api.skills.woodcutting.enums.AxeType;
import viking.api.skills.woodcutting.enums.TreeType;
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
     * @return The highest level axe the RSPlayer can use.
     */
    public AxeType currentAppropriateAxe() {
        final AxeType[] axes = AxeType.values();

        for (int i = axes.length - 1; i >= 0; i--)
            if (axes[i].getWoodcuttingLevel() <= skills.getStatic(Skill.WOODCUTTING))
                return axes[i];

        return AxeType.IRON;
    }

    /**
     * Gets the highest level tree that the RSPlayer can chop.
     *
     * @return The highest level tree the RSPlayer can chop
     */
    public TreeType getBestChoppableTreeType(boolean is_members) {
        final TreeType[] tree_types = TreeType.values();

        for (int i = tree_types.length - 1; i >= 0; i--) {
            if (tree_types[i].getWoodcuttingLevel() <= skills.getStatic(Skill.WOODCUTTING)) {
                if (tree_types[i].isMembers() && !is_members) {
                    continue;
                }
                return tree_types[i];
            }
        }

        return TreeType.NORMAL;
    }

}

