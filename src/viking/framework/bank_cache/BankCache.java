package viking.framework.bank_cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osbot.rs07.api.model.Item;

import viking.framework.script.VikingScript;

public class BankCache extends Thread
{
	private static final int CYCLE_TIME = 600;
	
	private final Map<Integer, Integer> BANK_CACHE = new HashMap<>();
	
	private VikingScript script;
	
	private List<Item> beforeDepositBox = new ArrayList<>(), afterDepositBox = new ArrayList<>();
	
	public BankCache(VikingScript s)
	{
		script = s;
	}
	
	public void run()
	{
		while(script.bot.getScriptExecutor().isRunning() || script.bot.getScriptExecutor().isPaused())
		{
			try
			{
				if(script.client.isLoggedIn())
				{
					if(script.bank.isOpen())
						updateBank();
					else if(script.depositBox.isOpen())
						prepareDepositBoxUpdate();
					else if(!beforeDepositBox.isEmpty()) //just got done using deposit box
						depositBoxUpdate();
				}
					
				sleep(CYCLE_TIME);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void prepareDepositBoxUpdate()
	{
		if(beforeDepositBox.isEmpty())
			beforeDepositBox.addAll(Arrays.asList(script.inventory.getItems()));
	}
	
	private void depositBoxUpdate()
	{
		script.log(this, false, "Deposit Box Update");
		afterDepositBox.addAll(Arrays.asList(script.inventory.getItems()));
		
		for(Item i : beforeDepositBox)
		{
			if(i == null)
				continue;
			
			if(!afterDepositBox.contains(i))
			{
				script.log(this, false, "Adding " + i.getId() + "x" + i.getAmount() + " to bank cache");
				BANK_CACHE.put(i.getId(), BANK_CACHE.getOrDefault(i.getId(), 0) + i.getAmount());
			}
		}
		
		beforeDepositBox.clear();
		afterDepositBox.clear();
	}
	
	private void updateBank()
	{
		script.log(this, false, "Update Bank");
		Item[] items = script.bank.getItems();
		if(items == null)
			return;
		
		BANK_CACHE.clear();
		for(Item i : items)
		{
			if(i == null)
				continue;
			
			BANK_CACHE.put(i.getId(), i.getAmount());
		}
	}
	
	public Map<Integer, Integer> get()
	{
		return BANK_CACHE;
	}
}
