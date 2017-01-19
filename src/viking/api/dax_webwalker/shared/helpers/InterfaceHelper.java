package viking.api.dax_webwalker.shared.helpers;



import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.osbot.rs07.api.ui.RS2Widget;

import viking.framework.VMethodProvider;

public class InterfaceHelper {

	
	private static VMethodProvider api;
	
	public InterfaceHelper(VMethodProvider api)
	{
		InterfaceHelper.api = api;
	}
    /**
     *
     * @param id
     * @return never null
     */
    public static List<RS2Widget> getAllInterfaces(int id){
        ArrayList<RS2Widget> interfaces = new ArrayList<>();
        Queue<RS2Widget> queue = new LinkedList<>();
        
        RS2Widget[] master = api.widgets.getWidgets(id);

        if (master == null || master.length == 0){
            return interfaces;
        }
        
        Collections.addAll(queue, master);

        while (!queue.isEmpty()){
            RS2Widget rsInterface = queue.poll();
            interfaces.add(rsInterface);
            RS2Widget[] children = rsInterface.getChildWidgets();
            if (children != null) {
                Collections.addAll(queue, children);
            }
        }

        return interfaces;
    }

    public static List<RS2Widget> getAllInterfaces(RS2Widget parent){
        ArrayList<RS2Widget> interfaces = new ArrayList<>();
        Queue<RS2Widget> queue = new LinkedList<>();

        if (parent == null){
            return interfaces;
        }

        queue.add(parent);
        while (!queue.isEmpty()){
            RS2Widget rsInterface = queue.poll();
            interfaces.add(rsInterface);
            RS2Widget[] children = rsInterface.getChildWidgets();
            if (children != null) {
                Collections.addAll(queue, children);
            }
        }

        return interfaces;
    }
}
