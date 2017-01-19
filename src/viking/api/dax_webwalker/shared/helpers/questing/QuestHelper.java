package viking.api.dax_webwalker.shared.helpers.questing;


import java.util.ArrayList;
import java.util.Arrays;

import org.osbot.rs07.api.ui.RS2Widget;

import viking.api.dax_webwalker.shared.helpers.InterfaceHelper;


public class QuestHelper {

    private static final int QUEST_MASTER_INTERFACE = 399;


    public static ArrayList<Quest> getAllQuests(){
        ArrayList<Quest> quests = new ArrayList<>();
        for (RS2Widget rsInterface : InterfaceHelper.getAllInterfaces(QUEST_MASTER_INTERFACE)){
            String[] actions = rsInterface.getInteractActions();
            if (actions == null){
                continue;
            }
            System.out.println(rsInterface.getMessage());
            if (Arrays.asList(actions).contains("Read Journal:")){
                quests.add(new Quest(rsInterface.getMessage(), Quest.State.getState(rsInterface.getTextColor())));
            }
        }
        return quests;
    }

}
