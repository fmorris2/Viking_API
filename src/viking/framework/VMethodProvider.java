package viking.framework;

import org.osbot.rs07.script.MethodProvider;

import viking.api.banking.BankUtils;
import viking.api.condition.VConditions;
import viking.api.depositbox.BankDepositBox;
import viking.api.filter.VFilters;
import viking.api.login.VLogin;
import viking.api.skills.fishing.Fishing;
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
    public BankDepositBox bankDepositBox;
    public WalkingUtils walkUtils;
    public VConditions conditions;
    public VFilters filters;
    public Averager avg;
    public Woodcutting woodcutting;
    public Fishing fishing;
    public VLogin login;

    @SuppressWarnings("deprecation")
    public void exchangeContext(VikingScript script) {
        super.exchangeContext(script.bot);
        this.script = script;
        bankUtils = script.getUtils().bank;
        bankDepositBox = script.getUtils().bankDepositBox;
        walkUtils = script.getUtils().walk;
        conditions = script.getUtils().conditions;
        filters = script.getUtils().filters;
        avg = script.getUtils().averager;
        woodcutting = script.getUtils().woodcutting;
        fishing = script.getUtils().fishing;
        login = script.getUtils().login;
    }
}
