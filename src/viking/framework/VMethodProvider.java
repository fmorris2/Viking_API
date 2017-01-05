package viking.framework;

import org.osbot.rs07.api.util.LocalPathFinder;
import org.osbot.rs07.script.MethodProvider;

import viking.api.banking.BankUtils;
import viking.api.condition.VConditions;
import viking.api.filter.VFilters;
import viking.api.interaction.VInteractionFactory;
import viking.api.item.ItemUtils;
import viking.api.item_managment.ItemManagment;
import viking.api.login.VLogin;
import viking.api.object.ObjectUtils;
import viking.api.position.PosUtils;
import viking.api.pricechecking.PriceChecking;
import viking.api.skills.fishing.Fishing;
import viking.api.skills.woodcutting.Woodcutting;
import viking.api.stats.Stats;
import viking.api.travel.WalkingUtils;
import viking.framework.averager.Averager;
import viking.framework.script.VikingScript;

/**
 * This class serves as an extension of MethodProvider in the
 * OSBot API. It allows various classes of our framework (worker, mission)
 * to extend it, and therefore all of the important classes in our implementation
 * will have direct access to the various script utilities, instead of having to
 * type extremely long chains of getters
 *
 * @author The Viking
 */
public class VMethodProvider extends MethodProvider {

    public VikingScript script;
    public BankUtils bankUtils;
    public WalkingUtils walkUtils;
    public VConditions conditions;
    public VFilters filters;
    public Averager avg;
    public Woodcutting woodcutting;
    public Fishing fishing;
    public VLogin login;
    public VInteractionFactory iFact;
    public ItemManagment item_managment;
    public PriceChecking price_checking;
    public Stats stats;
    public ItemUtils itemUtils;
    public ObjectUtils objectUtils;
    public LocalPathFinder localPathFinder;
    public PosUtils posUtils;

    @SuppressWarnings("deprecation")
    public void exchangeContext(VikingScript script) {
        super.exchangeContext(script.bot);
        this.script = script;
        bankUtils = script.getUtils().bank;
        walkUtils = script.getUtils().walk;
        conditions = script.getUtils().conditions;
        filters = script.getUtils().filters;
        avg = script.getUtils().averager;
        woodcutting = script.getUtils().woodcutting;
        fishing = script.getUtils().fishing;
        login = script.getUtils().login;
        item_managment = script.getUtils().item_managment;
        price_checking = script.getUtils().price_checking;
        stats = script.getUtils().stats;
        iFact = script.getUtils().iFact;
        itemUtils = script.getUtils().itemUtils;
        objectUtils = script.getUtils().objectUtils;
        localPathFinder = script.getUtils().localPathFinder;
        posUtils = script.getUtils().posUtils;
    }

    public void waitMs(long ms) {
        try {
            sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
