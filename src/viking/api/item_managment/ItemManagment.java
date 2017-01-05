package viking.api.item_managment;

import org.osbot.rs07.api.model.Item;
import viking.framework.VMethodProvider;

import java.util.ArrayList;

/**
 * Created by Sphiinx on 1/4/2017.
 */
public class ItemManagment extends VMethodProvider {

    /**
     * ArrayList containing all of the items we should try to buy.
     */
    private ArrayList<Item> items_needed = new ArrayList<>();

    /**
     * ArrayList containing all of the items we're willing to sell in order to buy the items_needed.
     */
    private ArrayList<Item> items_willing_to_sell = new ArrayList<>();


}

