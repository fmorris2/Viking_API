package viking.framework;

import org.osbot.rs07.api.util.LocalPathFinder;
import org.osbot.rs07.script.MethodProvider;

import viking.api.banking.BankUtils;
import viking.api.condition.VConditions;
import viking.api.dax_webwalker.DaxPath;
import viking.api.filter.VFilters;
import viking.api.grand_exchange.VGrandExchange;
import viking.api.interaction.VInteractionFactory;
import viking.api.item.ItemUtils;
import viking.api.login.VLogin;
import viking.api.object.ObjectUtils;
import viking.api.position.PosUtils;
import viking.api.pricechecking.PriceChecking;
import viking.api.questing.Questing;
import viking.api.skills.fishing.Fishing;
import viking.api.skills.mining.Mining;
import viking.api.skills.woodcutting.Woodcutting;
import viking.api.stats.Stats;
import viking.api.travel.WalkingUtils;
import viking.api.world.VHopper;
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
    public Mining mining;
    public Fishing fishing;
    public VLogin login;
    public VInteractionFactory iFact;
    public PriceChecking price_checking;
    public Stats stats;
    public ItemUtils itemUtils;
    public ObjectUtils objectUtils;
    public LocalPathFinder localPathFinder;
    public PosUtils posUtils;
    public VHopper hopper;
    public VGrandExchange vGe;
    public DaxPath daxPath;
    public Questing questing;

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
        mining = script.getUtils().mining;
        fishing = script.getUtils().fishing;
        login = script.getUtils().login;
        price_checking = script.getUtils().price_checking;
        stats = script.getUtils().stats;
        iFact = script.getUtils().iFact;
        itemUtils = script.getUtils().itemUtils;
        objectUtils = script.getUtils().objectUtils;
        localPathFinder = script.getUtils().localPathFinder;
        posUtils = script.getUtils().posUtils;
        hopper = script.getUtils().hopper;
        vGe = script.getUtils().vGe;
        daxPath = script.getUtils().daxPath;
        questing = script.getUtils().questing;
    }

    public void waitMs(long ms) {
        try {
            sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
