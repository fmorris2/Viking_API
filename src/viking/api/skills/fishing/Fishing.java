package viking.api.skills.fishing;

import org.osbot.rs07.api.ui.Skill;
import viking.api.skills.fishing.enums.FishType;
import viking.framework.VMethodProvider;

/**
 * Created by Sphiinx on 1/3/2017.
 */
public class Fishing extends VMethodProvider {

    /**
     * Gets the highest level fish that the RSPlayer can fish.
     *
     * @return The highest level tree the RSPlayer can chop
     */
    public FishType getBestFishableFish(boolean is_members) {
        final FishType[] tree_types = FishType.values();

        for (int i = tree_types.length - 1; i >= 0; i--) {
            if (tree_types[i].getFishingLevel() <= skills.getStatic(Skill.FISHING)) {
                if (tree_types[i].isMembers() && !is_members) {
                    continue;
                }
                return tree_types[i];
            }
        }

        return FishType.SHRIMP;
    }

}

