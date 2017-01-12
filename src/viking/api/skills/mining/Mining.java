package viking.api.skills.mining;

import org.osbot.rs07.api.ui.Skill;
import viking.api.skills.mining.enums.PickaxeType;
import viking.api.skills.mining.enums.RockType;
import viking.framework.VMethodProvider;

/**
 * Created by Sphiinx on 12/27/2016.
 */
public class Mining extends VMethodProvider {

    /**
     * Gets the best usable Pickaxe the RSPlayer has.
     *
     * @param is_checking_bank True if we are checking the BANK for the best usable Pickaxe; false otherwise.
     * @return The best usable Pickaxe the RSPlayer has.
     */
    public PickaxeType getBestUsablePickaxe(boolean is_checking_bank) {
        final PickaxeType[] pickaxes = PickaxeType.values();

        for (int i = pickaxes.length - 1; i >= 0; i--) {
            PickaxeType pickaxe = pickaxes[i];
            if (pickaxe.getMiningLevel() <= skills.getStatic(Skill.MINING) &&
                    ((inventory.getAmount(pickaxe.getItemID()) > 0 || equipment.getAmount(pickaxe.getItemID()) > 0)
                            || (is_checking_bank && bank.getItem(pickaxe.getItemID()) != null)))
                return pickaxe;
        }

        return null;
    }

    /**
     * Gets the highest level of Pickaxe the RSPlayer can use.
     *
     * @return The highest level Pickaxe the RSPlayer can use.
     */
    public PickaxeType currentAppropriatePickaxe() {
        final PickaxeType[] pickaxes = PickaxeType.values();

        for (int i = pickaxes.length - 1; i >= 0; i--)
            if (pickaxes[i].getMiningLevel() <= skills.getStatic(Skill.MINING))
                return pickaxes[i];

        return PickaxeType.IRON;
    }

    /**
     * Gets the highest level rock that the RSPlayer can mine.
     *
     * @return The highest level rock the RSPlayer can mine
     */
    public RockType getBestMineableRockType(boolean is_members) {
        final RockType[] rock_types = RockType.values();

        for (int i = rock_types.length - 1; i >= 0; i--) {
            if (rock_types[i].getMiningLevel() <= skills.getStatic(Skill.MINING)) {
                if (rock_types[i].isMembers() && !is_members) {
                    continue;
                }
                return rock_types[i];
            }
        }

        return RockType.CLAY;
    }

}

