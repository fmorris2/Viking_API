package viking.framework.item_management;

import org.osbot.rs07.api.Bank.BankMode;

import viking.api.banking.enums.BankLocation;
import viking.framework.VMethodProvider;
import viking.framework.script.VikingScript;

public class ItemManagementEvent
{
	private final VikingScript SCRIPT;
	private final VMethodProvider API;
	private final IMEntry TO_BUY;
	private final ItemManagementTracker TRACKER;
	private final int[] TO_SELL;
	
	private boolean isFinished;
	private boolean hasWithdrawnSellables;
	
	public ItemManagementEvent(VikingScript script, VMethodProvider api, IMEntry toBuy, ItemManagementTracker tracker)
	{
		SCRIPT = script;
		API = api;
		TO_BUY = toBuy;
		TRACKER = tracker;
		TO_SELL = tracker.TO_SELL;
	}
	
	public void execute()
	{
		if(!API.client.isLoggedIn())
			API.login.login(API.client.getUsername(), SCRIPT.PARAMS.get("pass"));
		else if(!isAtGE()) //first, we have to go to the GE
			goToGE();
		else //at GE, commence the selling / buying process
		{
			SCRIPT.log(this, false, "At GE");
			//check if we have enough gold in inventory
			long invGold = API.inventory.getAmount(995);
			if(invGold >= TO_BUY.PRICE)
				handleBuying();
			else if(TRACKER.getTotalGp() >= TO_BUY.PRICE) //we have enough gold total, but there is some left in the bank
				withdrawGold();
			else //we need to sell some things in order to have enough gold
				handleSelling();
		}
	}
	
	private void handleBuying()
	{
		SCRIPT.log(this, false, "handleBuying()");
		if(API.bank.isOpen())
			API.bank.close();
	}
	
	private void withdrawGold()
	{
		SCRIPT.log(this, false, "withdrawGold()");
		
		withdraw(995);
	}
	
	private void handleSelling()
	{
		SCRIPT.log(this, false, "handleSelling()");
		
		if(!hasWithdrawnSellables)
		{
			SCRIPT.log(this, false, "Withdrawing sellables");
			hasWithdrawnSellables = withdraw(TO_SELL);
		}
		else
		{
			SCRIPT.log(this, false, "Has withdrawn sellables.... Time to sell them");
		}
	}
	
	private boolean withdraw(int... ids)
	{
		if(API.bank.isOpen() && API.bank.enableMode(BankMode.WITHDRAW_NOTE) & API.bank.depositAllExcept(995))
		{
			if(API.bank.contains(995) && !API.bank.withdrawAll(995)) //make sure to withdraw any gold if we have it
				return false;
			
			for(int id : ids)
				if(API.bank.contains(id) && !API.bank.withdrawAll(id))
					return false;
			
			return API.bank.close();
		}
		else
			API.bankUtils.open();
		
		return false;
	}
	
	private boolean isAtGE()
	{
		return BankLocation.GRAND_EXCHANGE.getArea().contains(API.myPosition());
	}
	
	private void goToGE()
	{
		SCRIPT.log(this, false, "Go to GE");
		API.walkUtils.walkToArea(BankLocation.GRAND_EXCHANGE.getArea());
	}
	
	public boolean isFinished()
	{
		return isFinished;
	}
}
