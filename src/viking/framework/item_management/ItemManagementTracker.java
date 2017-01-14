package viking.framework.item_management;

import java.util.HashMap;
import java.util.Map;

import viking.api.pricechecking.PriceChecking;
import viking.framework.mission.Mission;
import viking.framework.script.VikingScript;

public class ItemManagementTracker
{
	public static final Map<Integer, Integer> PRICE_CACHE = new HashMap<>();
	public static final double SELL_PRICE_MOD = 0.7;
	
	private static final int GOLD_ID = 995;
	
	public final VikingScript SCRIPT;
	public final ItemManagement IM;
	public final int[] TO_SELL;
	private final IMEntry[] TO_BUY;
	
	private long totalGp;
	private long totalSellableItemValue;
	
	public ItemManagementTracker(VikingScript s, ItemManagement im)
	{
		SCRIPT = s;
		IM = im;
		TO_SELL = im.itemsToSell();
		TO_BUY = im.itemsToBuy();
	}
	
	public void update()
	{
		Mission m = SCRIPT.getMissionHandler().getCurrent();
		
		//update gold amount
		long invGp = m.inventory.getAmount(GOLD_ID);
		long bankGp = SCRIPT.BANK_CACHE.get().getOrDefault(GOLD_ID, 0);
		//SCRIPT.log(this, false, "invGp: " + invGp + ", bankGp: " + bankGp + ", totalGp: " + (invGp + bankGp));
		totalGp = invGp + bankGp;
		
		//update total sellable item value
		totalSellableItemValue = 0;
		
		for(int id : TO_SELL)
		{
			//determine amount we have of the specific item to sell
			long invAmt = m.inventory.getAmount(id);
			long bankAmt = SCRIPT.BANK_CACHE.get().getOrDefault(id, 0);
			//SCRIPT.log(this, false, "Amount for item id " + id + ": inv- " + invAmt + ", bank - " + bankAmt);
			
			//determine value of how many of this item we have to sell
			Integer price = PRICE_CACHE.get(id);
			if(price == null)
			{
				price = PriceChecking.getGEPrice(id);
				PRICE_CACHE.put(id, price);
			}
			
			totalSellableItemValue += (invAmt + bankAmt) * (price * SELL_PRICE_MOD);
		}
		
		//SCRIPT.log(this, false, "Total sellable item value: " + totalSellableItemValue);
		//SCRIPT.log(this, false, "Total Vaue: " + getTotalValue() + " gp");
	}
	
	public IMEntry needsToBuy()
	{
		for(IMEntry e : TO_BUY)
		{
			if(e.shouldBuy(getTotalValue()))
				return e;
		}
		
		return null;
	}
	
	public long getTotalGp()
	{
		return totalGp;
	}
	
	public long getTotalValue()
	{
		return totalGp + totalSellableItemValue;
	}
}
