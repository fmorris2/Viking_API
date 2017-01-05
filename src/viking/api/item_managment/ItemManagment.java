package viking.api.item_managment;

import org.osbot.rs07.api.model.Item;
import viking.framework.VMethodProvider;

import java.util.ArrayList;

/**
 * Created by Sphiinx on 1/4/2017.
 */
public class ItemManagment extends VMethodProvider {

    /**
     * ArrayList containing all of the itemIDs we should try to buy.
     */
    public ArrayList<Integer> items_needed = new ArrayList<>();

    /**
     * ArrayList containing all of the itemIDs we're willing to sell in order to buy the items_needed.
     */
    public ArrayList<Integer> items_willing_to_sell = new ArrayList<>();


}

