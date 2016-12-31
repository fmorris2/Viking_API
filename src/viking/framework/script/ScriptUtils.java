package viking.framework.script;

import viking.api.banking.BankUtils;
import viking.api.condition.VConditions;
import viking.api.filter.VFilters;
import viking.api.login.VLogin;
import viking.api.skills.woodcutting.Woodcutting;
import viking.api.travel.WalkingUtils;
import viking.framework.averager.Averager;

/**
 * Each VikingScript has a ScriptUtils object which hold various other
 * utility objects from the Viking API
 *
 * @author The Viking
 */
public class ScriptUtils {

    public final BankUtils BANK = new BankUtils();
    public final WalkingUtils WALK = new WalkingUtils();
    public final VConditions CONDITIONS = new VConditions();
    public final VFilters FILTERS = new VFilters();
    public final Averager AVERAGER = new Averager();
    public final Woodcutting WOODCUTTING = new Woodcutting();
    public final VLogin LOGIN = new VLogin();
    
    @SuppressWarnings("deprecation")
	public ScriptUtils(VikingScript script)
    {
        BANK.exchangeContext(script.bot);
        WALK.exchangeContext(script.bot);
        CONDITIONS.exchangeContext(script.bot);
        FILTERS.exchangeContext(script.bot);
        AVERAGER.exchangeContext(script.bot);
        WOODCUTTING.exchangeContext(script.bot);
        LOGIN.exchangeContext(script.bot);
    }
}
