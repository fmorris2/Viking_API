package viking.api.questing;

import org.osbot.rs07.api.ui.RS2Widget;
import viking.api.Timing;
import viking.framework.VMethodProvider;

/**
 * Created by Sphiinx on 1/19/2017.
 */
public class Questing extends VMethodProvider {

    private final int QUEST_COMPLETION_INTERFACE = 277;
    private final int QUEST_COMPLETION_CLOSE_INTERFACE = 15;

    /**
     * Closes the quest completion dialogue.
     *
     * @return True if the quest completion dialogue was successfully closed; false otherwise.
     * */
    public boolean closeQuestCompletion() {
        final RS2Widget QUEST_CLOSE_BUTTON = widgets.get(QUEST_COMPLETION_INTERFACE, QUEST_COMPLETION_CLOSE_INTERFACE);

        if (QUEST_CLOSE_BUTTON == null)
            return true;

        if (QUEST_CLOSE_BUTTON.interact())
            return Timing.waitCondition(() -> widgets.get(QUEST_COMPLETION_INTERFACE, QUEST_COMPLETION_CLOSE_INTERFACE) == null, 150, random(2000, 2500));

        return false;
    }

}

