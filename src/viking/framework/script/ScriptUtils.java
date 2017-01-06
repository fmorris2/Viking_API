package viking.framework.script;

import org.osbot.rs07.api.util.LocalPathFinder;

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
    public ItemManagment item_managment;
    public PriceChecking price_checking;
    public Stats stats;
    public ItemUtils itemUtils;
    public ObjectUtils objectUtils;
    public LocalPathFinder localPathFinder;
    public PosUtils posUtils;

    public void init(VikingScript script) {
    	
        bank = new BankUtils();
        walk = new WalkingUtils();
        conditions = new VConditions();
        filters = new VFilters();
        averager = new Averager();
        woodcutting = new Woodcutting();
        fishing = new Fishing();
        login = new VLogin(script);
        iFact = new VInteractionFactory();
        item_managment = new ItemManagment();
        price_checking = new PriceChecking();
        stats = new Stats();
        itemUtils = new ItemUtils();
        objectUtils = new ObjectUtils();
        localPathFinder = new LocalPathFinder(script.bot);
        posUtils = new PosUtils();

        bank.exchangeContext(script);
        walk.exchangeContext(script);
        conditions.exchangeContext(script);
        filters.exchangeContext(script);
        averager.exchangeContext(script);
        woodcutting.exchangeContext(script);
        login.exchangeContext(script);
        iFact.exchangeContext(script);
        item_managment.exchangeContext(script);
        price_checking.exchangeContext(script);
        stats.exchangeContext(script);
        itemUtils.exchangeContext(script);
        objectUtils.exchangeContext(script);
        posUtils.exchangeContext(script);

    }

}
