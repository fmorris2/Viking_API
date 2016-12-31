package viking.framework;

import org.osbot.rs07.script.MethodProvider;

import viking.api.banking.BankUtils;
import viking.api.condition.VConditions;
import viking.api.filter.VFilters;
import viking.api.login.VLogin;
import viking.api.skills.woodcutting.Woodcutting;
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
    public VLogin login;

    @SuppressWarnings("deprecation")
    public void exchangeContext(VikingScript script) {
        super.exchangeContext(script.bot);
        this.script = script;
        bankUtils = script.getUtils().BANK;
        walkUtils = script.getUtils().WALK;
        conditions = script.getUtils().CONDITIONS;
        filters = script.getUtils().FILTERS;
        avg = script.getUtils().AVERAGER;
        woodcutting = script.getUtils().WOODCUTTING;
        login = script.getUtils().LOGIN;
        
        bankUtils.exchangeContext(script.bot);
        walkUtils.exchangeContext(script.bot);
        conditions.exchangeContext(script.bot);
        filters.exchangeContext(script.bot);
        avg.exchangeContext(script.bot);
        woodcutting.exchangeContext(script.bot);
        login.exchangeContext(script.bot);
    }
}
