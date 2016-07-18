package viking.api.banking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.osbot.rs07.api.map.Area;

import viking.api.ScriptUtil;
import viking.framework.script.VikingScript;

/**
 * Utility class with methods which relate to banking
 * 
 * @author The Viking
 * 
 */
public class BankUtils extends ScriptUtil
{
	//Our bank cache, so we don't have to continue to load up the VikingBank value array
	private static final List<VikingBank> BANKS = new ArrayList<>(Arrays.asList(VikingBank.values()));
		
	//Our cached Comparator, so we don't have to create a new one every time we want to sort the banks
	private final Comparator<VikingBank> BANK_COMPARATOR = bankComparator();
	
	public BankUtils(VikingScript script)
	{
		super(script);
	}
	
	/**
	 * This method uses our handy VikingBank enum
	 * to calculate and determine the closest bank to
	 * the character. Takes into consideration whether
	 * or not the character is in a members world.
	 * 
	 * @return the closest valid bank to the player
	 */
	public Area getClosest()
	{
		Collections.sort(BANKS, BANK_COMPARATOR); //Sort cached banks list using comparator
		script.log(this, true, "Closest bank: " + BANKS.get(0));
		return BANKS.get(0).getArea();
	}
	
	/**
	 * This method opens the closest bank. The only reason
	 * this method is here is due to the fact that bank.open()
	 * throws an InterruptedException, so instead of adding
	 * a try / catch block whenever we use it in our code,
	 * we simply make this method and not have to worry about
	 * it again
	 * 
	 * @return whether or not the bank was opened successfully
	 */
	public boolean open()
	{
		try
		{
			return bank.open();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * This is the comparator we use to sort our bank cache, and
	 * push the best bank option to the front of the list
	 * 
	 * @return a Comparator which sorts by whether or not the
	 * bank is valid first, and distance to the player second
	 */
	private Comparator<VikingBank> bankComparator()
	{
		return new Comparator<VikingBank>()
		{
			@Override
			public int compare(VikingBank one, VikingBank two)
			{
				final boolean IN_MEMBERS_WORLD = script.worlds.isMembersWorld();
				
				//Compare first by whether or not the banks are valid for the character in terms of membership
				if(!IN_MEMBERS_WORLD)
				{
					final int MEM_DIFF = Boolean.compare(one.isMembers(), two.isMembers());
					if(MEM_DIFF != 0)
						return MEM_DIFF;
				}
				
				//Compare second by distance to the player
				return script.myPosition().distance(one.getArea().getRandomPosition()) - script.myPosition().distance(two.getArea().getRandomPosition());
			}
		};
	}
}
