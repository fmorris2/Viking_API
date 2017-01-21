package viking.framework.mule;

import java.util.Arrays;

import viking.api.pricechecking.PriceChecking;
import viking.framework.script.VikingScript;

public class MuleOrder
{	
	public final int[] ITEMS;
	
	private int[] prices;
	
	public int muleAt;
	private int currentNetWorth;
	
	private VikingScript script;
	
	public MuleOrder(VikingScript s, int... items)
	{
		script = s;
		ITEMS = items;
	}
	
	public boolean isReady()
	{
		if(prices == null)
		{
			prices = new int[ITEMS.length];
			getPrices();
		}
		
		updateNetWorth();
		return currentNetWorth >= muleAt;
	}
	
	private void updateNetWorth()
	{
		currentNetWorth = 0;
		for(int i = 0; i < ITEMS.length; i++)
			currentNetWorth += script.BANK_CACHE.get().getOrDefault(ITEMS[i], 0) * prices[i];
	}
	
	private void getPrices()
	{
		script.log(this, false, "Get prices");
		for(int i = 0; i < ITEMS.length; i++)
		{
			int price = PriceChecking.getGEPrice(ITEMS[i]);
			prices[i] = price == -1 ? PriceChecking.getOSBuddyPrice(ITEMS[i]) : price;
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(ITEMS);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MuleOrder other = (MuleOrder) obj;
		if (!Arrays.equals(ITEMS, other.ITEMS))
			return false;
		return true;
	}
	
	
}
