package viking.framework.mule;

import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.api.Bank.BankMode;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.Player;

import viking.api.Timing;
import viking.framework.script.VikingScript;

public class MuleOrderEvent
{
	private static final int MULE_DIST_THRESH = 10;
	private VikingScript script;
	private MuleOrder order;
	private Position mulePos;
	private String muleName;
	private boolean hasFinished, hasWalkedToMule, hasWithdrawnOrder, hasDepositedAll;
	private List<Item> toTrade = new ArrayList<>();
	
	public MuleOrderEvent(VikingScript s, MuleOrder o)
	{
		script = s;
		order = o;
	}
	
	public void execute()
	{
		script.log(this, false, "MuleOrderEvent execute");
		
		if(muleName != null && mulePos != null)	
		{
			script.log(this, false, "Has mule info...");
			if(!hasWalkedToMule && walkToMule())
				hasWalkedToMule = true;
			
			if(hasWalkedToMule)
			{
				if(!hasWithdrawnOrder)
					withdrawOrder();
				else if(walkToMule())
					tradeMule();
			}
			
			
				
		}
		else
			script.log(this, false, "Mule info has not been provided yet...");
	}
	
	private void tradeMule()
	{
		script.log(this, false , "trade mule");
		Player mule = script.players.closest(muleName);
		if(mule != null)
		{
			script.log(this, false, "Mule found. Attempting to trade...");
			if(script.bank.isOpen())
				script.bank.close();
			if(!script.trade.isCurrentlyTrading())
			{
				toTrade.clear();
				for(Item i : script.getInventory().getItems())
					if(i != null && (i.isNote() || i.getNotedId() == -1)) //if item is noted or stackable
						toTrade.add(i);
			}
				
			if(script.trade.isCurrentlyTrading() ||
					(mule.interact("Trade with") && Timing.waitCondition(() -> script.trade.isCurrentlyTrading(), 9000)))
			{
				script.log(this, false, "Currently in trade with mule...");
				if(!script.trade.getOtherPlayer().equals(muleName))
				{
					script.log(this, false, "In trade with wrong player.... closing");
					script.trade.declineTrade();
				}
				
				if(offerItems() || script.trade.isSecondInterfaceOpen())
				{
					script.log(this, false, "Order offered... Accepting through trade");
					
					boolean firstWindow = script.trade.isFirstInterfaceOpen();
					if(script.trade.acceptTrade())
					{
						if(!firstWindow)
						{
							script.log(this, false, "Successfully traded mule. Order complete.");
							hasWalkedToMule = false;
							hasWithdrawnOrder = false;
							hasDepositedAll = false;
							hasFinished = true;
						}
					}
				}
			}
		}
		else
			script.log(this, false, "Waiting for mule to login...");
	}
	
	private boolean offerItems()
	{
		script.log(this, false, "Offer Items");
		for(Item i : toTrade)
			script.trade.offer(i.getId(), 0);
		
		Item[] offer = script.trade.getOurOffers().getItems();
		int count = 0;
		for(Item i : offer)
			if(i != null)
				count++;
		
		return count == toTrade.size();
	}
	
	private boolean walkToMule()
	{
		script.log(this, false, "Walk to mule");
		if(script.myPosition().distance(mulePos) <= MULE_DIST_THRESH)
			return true;
		
		return script.getUtils().walk.walkTo(mulePos)
					&& Timing.waitCondition(() -> script.myPosition().distance(mulePos) <= MULE_DIST_THRESH, 3500);
	}
	
	private void withdrawOrder()
	{
		script.log(this, false, "withdrawOrder()");
		
		if(!script.getUtils().bank.isInBank())
			script.getWalking().webWalk(script.getUtils().bank.getAllBanks(false, false));
		else if(!script.getBank().isOpen())
			script.getUtils().bank.open();
		else if(script.getUtils().bank.isNoteOn() || (script.bank.enableMode(BankMode.WITHDRAW_NOTE) && script.getUtils().bank.waitForNote()))
		{
			if(!hasDepositedAll && script.bank.depositAll())
				hasDepositedAll = true;
			else
			{
				boolean success = true;
				for(int i : order.ITEMS)
					if(script.bank.contains(i) && !script.bank.withdrawAll(i))
					{
						success = false;
						break;
					}
				
				hasWithdrawnOrder = success;
			}
		}
	}
	
	public boolean shouldExecute()
	{
		return order.isReady();
	}
	
	public boolean hasFinished()
	{
		return hasFinished;
	}
	
	public void setHasFinished(boolean b)
	{
		hasFinished = b;
	}
	
	public void setMuleName(String n)
	{
		muleName = n;
	}
	
	public void setMulePos(Position p)
	{
		mulePos = p;
	}
	
	public MuleOrder getOrder()
	{
		return order;
	}
}
