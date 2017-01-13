package viking.framework.item_management;

import org.osbot.rs07.api.Bank.BankMode;
import org.osbot.rs07.api.GrandExchange.Box;
import org.osbot.rs07.api.GrandExchange.Status;
import org.osbot.rs07.api.model.Item;

import viking.api.Timing;
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
	
	private boolean isFinished, hasWithdrawnSellables, hasPutInOffer;
	private Box box;
	
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
			API.login.login(API.bot.getUsername(), SCRIPT.PARAMS.get("pass"));
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
		if(API.inventory.contains(TO_BUY.ID))
			isFinished = true;
		else if(API.bank.isOpen())
			API.bank.close();
		else if(!API.grandExchange.isOpen())
		{
			SCRIPT.log(this, false, "Open GE");
			openGe();
		}
		else if(!hasPutInOffer) //GE is open & hasn't put in offer
		{
			SCRIPT.log(this, false, "Buy item");
			if((box = getBox(TO_BUY.ID)) != null) //if there is already an existing offer in for the item
				hasPutInOffer = true;
			else if(API.grandExchange.buyItem(TO_BUY.ID, TO_BUY.SEARCH_TERM, TO_BUY.PRICE, TO_BUY.AMT)
					&& Timing.waitCondition(() -> (box = getBox(TO_BUY.ID)) != null, 6000))
			{
				SCRIPT.log(this, false, "Successfully put in offer");
				hasPutInOffer = true;
			}
			else
				API.grandExchange.close();
		}
		else //GE is open & has put in offer
		{
			SCRIPT.log(this, false, "Waiting for buy offer to complete");
			if(box == null)
				box = getBox(TO_BUY.ID);
			
			if(box != null && API.grandExchange.getStatus(box) == Status.FINISHED_BUY)
			{
				SCRIPT.log(this, false, "Offer completed, collecting...");
				if(API.grandExchange.collect())
				{
					SCRIPT.log(this, false, "Successfully collected item");
					isFinished = true;
				}
			}
		}
	}
	
	private Box getBox(int id)
	{
		for(Box box : Box.values())
			if(API.grandExchange.getItemId(box) == id)
				return box;
		
		return null;
	}
	
	private boolean canCollect()
	{
		for(Box box : Box.values())
			if(box != null && API.grandExchange.getStatus(box) == Status.FINISHED_SALE)
				return true;
		
		return false;
	}
	
	private Box getFreeBox()
	{
		for(Box box : Box.values())
			if(box != null && API.grandExchange.getStatus(box) == Status.EMPTY)
				return box;
		
		return null;
	}
	
	private void withdrawGold()
	{
		SCRIPT.log(this, false, "withdrawGold()");
		
		withdraw(995);
	}
	
	private void openGe()
	{
		if(API.iFact.clickObject("Exchange", "Grand Exchange booth", 15).execute())
			Timing.waitCondition(() -> API.grandExchange.isOpen(), 3500);
	}
	
	private void handleSelling()
	{
		SCRIPT.log(this, false, "handleSelling()");
		
		if(!hasWithdrawnSellables)
		{
			SCRIPT.log(this, false, "Withdrawing sellables");
			hasWithdrawnSellables = withdraw(TO_SELL);
		}
		else //has withdrawn sellables
		{
			SCRIPT.log(this, false, "Has withdrawn sellables.... Time to sell them");
			if(!API.grandExchange.isOpen())
				openGe();
			else //ge is open
				offerItems();
		}
	}
	
	private void offerItems()
	{
		while(API.inventory.getAmount(995) < TO_BUY.PRICE)
		{
			if(canCollect())
				API.grandExchange.collect();
			
			for(int i : TO_SELL)
			{
				for(int idMod = 0; idMod < 2; idMod++) //to deal with noted items
				{
					int modifiedId = (i + idMod);
					Item invItem = API.inventory.getItem(modifiedId);
					
					if(invItem == null) //not in inv.... skip this item
						continue;
					else //attempt to offer item
					{
						Box sellBox = getFreeBox();
						if(sellBox != null && (API.grandExchange.isOfferScreenOpen() || API.grandExchange.sellItems(sellBox)))
						{
							if(invItem.interact() 
									&& API.grandExchange.setOfferPrice((int)(ItemManagementTracker.PRICE_CACHE.get(modifiedId - idMod) * ItemManagementTracker.SELL_PRICE_MOD))
									&& API.grandExchange.confirm())
							{
								SCRIPT.log(this, false, "Successfully put in sell offer for item");
							}
						}
					}
						
				}
			}
			
			API.waitMs(600);
		}
	}
	
	private boolean withdraw(int... ids)
	{
		if(API.bank.isOpen() && API.bank.enableMode(BankMode.WITHDRAW_NOTE) & API.bank.depositAllExcept(995))
		{
			SCRIPT.updateBankCache();
			
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
