package viking.framework.mule;

import viking.api.pricechecking.PriceChecking;
import viking.framework.script.VikingScript;

public class MuleOrder
{
	public final int[] ITEMS;
	
	private final int[] PRICES;
	
	public int muleAt;
	private int currentNetWorth;
	
	private VikingScript script;
	
	public MuleOrder(VikingScript s, int... items)
	{
		script = s;
		ITEMS = items;
		PRICES = new int[items.length];
		getPrices();
	}
	
	public boolean isReady()
	{
		updateNetWorth();
		return currentNetWorth >= muleAt;
	}
	
	private void updateNetWorth()
	{
		currentNetWorth = 0;
		for(int i : ITEMS)
			currentNetWorth += script.BANK_CACHE.get().getOrDefault(i, 0);
	}
	
	private void getPrices()
	{
		script.log(this, false, "Get prices");
		for(int i = 0; i < ITEMS.length; i++)
			PRICES[i] = PriceChecking.getGEPrice(i);
	}
	
	private int getNetWorth()
	{
		return currentNetWorth;
	}
}
