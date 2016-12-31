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

    public final BankUtils BANK;
    public final WalkingUtils WALK;
    public final VConditions CONDITIONS;
    public final VFilters FILTERS;
    public final Averager AVERAGER;
    public final Woodcutting WOODCUTTING;
    public final VLogin LOGIN;

    public ScriptUtils(VikingScript script) {
        BANK = new BankUtils(script);
        WALK = new WalkingUtils(script);
        CONDITIONS = new VConditions(script);
        FILTERS = new VFilters(script);
        AVERAGER = new Averager(script);
        WOODCUTTING = new Woodcutting(script);
        LOGIN = new VLogin(script);
    }
}
