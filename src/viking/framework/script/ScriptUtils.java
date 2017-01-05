package viking.framework.script;

import viking.api.banking.BankUtils;
import viking.api.condition.VConditions;
import viking.api.filter.VFilters;
import viking.api.interaction.VInteractionFactory;
import viking.api.login.VLogin;
import viking.api.skills.fishing.Fishing;
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

    public BankUtils bank;
    public WalkingUtils walk;
    public VConditions conditions;
    public VFilters filters;
    public Averager averager;
    public Woodcutting woodcutting;
    public Fishing fishing;
    public VLogin login;
    public VInteractionFactory iFact;
    
    public void init(VikingScript script)
    {
    	bank = new BankUtils();
    	walk = new WalkingUtils();
    	conditions = new VConditions();
    	filters = new VFilters();
    	averager = new Averager();
    	woodcutting = new Woodcutting();
    	fishing = new Fishing();
    	login = new VLogin(script);
    	iFact = new VInteractionFactory(); 
    	
    	bank.exchangeContext(script);
    	walk.exchangeContext(script);
    	conditions.exchangeContext(script);
    	filters.exchangeContext(script);
    	averager.exchangeContext(script);
    	woodcutting.exchangeContext(script);
    	login.exchangeContext(script);
    	iFact.exchangeContext(script);
    }
}
