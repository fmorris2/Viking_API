package viking.framework.item_management;

import viking.api.pricechecking.PriceChecking;
import viking.framework.goal.Goal;
import viking.framework.goal.GoalList;
import viking.framework.mission.Mission;

public class IMEntry
{
	private static final double BUY_PRICE_MOD = 1.5;
	
	public final int ID, AMT, PRICE, VALUE_NEEDED;
	public final String SEARCH_TERM;
	public final GoalList GOALS;
	
	private Mission mission;
	
	public IMEntry(Mission m, int id, int amt, String searchTerm, Goal... goals)
	{
		mission = m;
		ID = id;
		AMT = amt;
		PRICE = (int)(PriceChecking.getGEPrice(ID) * BUY_PRICE_MOD);
		VALUE_NEEDED = PRICE * AMT;
		SEARCH_TERM = searchTerm;
		GOALS = new GoalList(goals);
	}
	
	public IMEntry(Mission m, int id, int amt, int price, String searchTerm, Goal... goals)
	{
		mission = m;
		ID = id;
		AMT = amt;
		SEARCH_TERM = searchTerm;
		PRICE = price;
		VALUE_NEEDED = PRICE * AMT;
		GOALS = new GoalList(goals);
	}
	
	public boolean shouldBuy(long totalValue)
	{
		return !mission.getScript().BANK_CACHE.get().isEmpty() && 
				totalValue >= VALUE_NEEDED && !playerHasEntry() && GOALS.hasReachedGoals();
	}
	
	private boolean playerHasEntry()
	{
		if(!mission.myPlayer().isVisible()) //just in case equipment / inv is not loaded
			return true;
		
		boolean inInv = mission.inventory.contains(ID, ID+1);
		boolean inEquip = mission.equipment.contains(ID);
		boolean inBank = mission.getScript().BANK_CACHE.get().containsKey(ID);
		
		return inInv || inEquip || inBank;
	}
	
	public String toString()
	{
		return "[IMEntry] ID: " + ID + " AMT: " + AMT + " PRICE: " + PRICE + " SEARCH_TERM: " + SEARCH_TERM;
	}
}
