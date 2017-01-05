package viking.api.item_managment;

import viking.framework.VMethodProvider;

/**
 * Created by Sphiinx on 1/4/2017.
 */
public class ItemManagment extends VMethodProvider {

    /**
     * ArrayList containing all of the itemIDs we should try to buy.
     */
    public int[] items_needed;

    /**
     * ArrayList containing all of the itemIDs we're willing to sell in order to buy the items_needed.
     */
    public int[] items_willing_to_sell;


}

