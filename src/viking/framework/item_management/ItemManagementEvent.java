package viking.framework.item_management;

import viking.api.banking.enums.BankLocation;
import viking.framework.VMethodProvider;

public class ItemManagementEvent
{
	private final VMethodProvider API;
	private final IMEntry TO_BUY;
	private final ItemManagementTracker TRACKER;
	private final int[] TO_SELL;
	
	private boolean isFinished;
	
	public ItemManagementEvent(VMethodProvider api, IMEntry toBuy, ItemManagementTracker tracker)
	{
		API = api;
		TO_BUY = toBuy;
		TRACKER = tracker;
		TO_SELL = tracker.TO_SELL;
	}
	
	public void execute()
	{
		//first, we have to go to the GE
		if(!isAtGE())
			goToGE();
		else //at GE, commence the selling / buying process
		{
			API.log("[IME]: At GE");
		}
	}
	
	private boolean isAtGE()
	{
		return BankLocation.GRAND_EXCHANGE.getArea().contains(API.myPosition());
	}
	
	private void goToGE()
	{
		API.log("[IME]: Go to GE");
		API.walkUtils.walkToArea(BankLocation.GRAND_EXCHANGE.getArea());
	}
	
	public boolean isFinished()
	{
		return isFinished;
	}
}
