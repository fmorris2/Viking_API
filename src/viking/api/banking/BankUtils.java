package viking.api.banking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.osbot.rs07.api.map.Area;

import viking.api.banking.enums.BankLocation;
import viking.framework.VMethodProvider;

/**
 * Utility class with methods which relate to banking
 * 
 * @author The Viking
 * 
 */
public class BankUtils extends VMethodProvider
{
	//Our BANK cache, so we don't have to continue to load up the VikingBank value array
	private static final List<BankLocation> BANK_LOCATIONS = new ArrayList<>(Arrays.asList(BankLocation.values()));
		
	//Our cached Comparator, so we don't have to create a new one every time we want to sort the banks
	private Comparator<BankLocation> bankComparator = bankComparator();

	/**
	 * Gets an Area Array containing all of the banks in the BankLocation enum.
	 *
	 * @param is_members True if we should return all banks, false if we should only return f2p.
	 * @param deposit_box True if we should return deposit boxes as well, false if we should only return banks.
	 * @return An Area Array containing all of the bank areas.
	 * */
	public Area[] getAllBanks(boolean is_members, boolean deposit_box) {
		BankLocation[] bank_locations = BankLocation.values();
		List<Area> bank_areas = new ArrayList<>();
		for (BankLocation bank_location : bank_locations) {
			if (bank_location.isMembers() && !is_members)
				continue;

			if (bank_location.isDepositBox() && !deposit_box)
				continue;

			bank_areas.add(bank_location.getArea());
		}

		return bank_areas.toArray(new Area[bank_areas.size()]);
	}

	/**
	 * This method uses our handy VikingBank enum
	 * to calculate and determine the closest BANK to
	 * the character. Takes into consideration whether
	 * or not the character is in a members world.
	 *
	 * @return the closest valid BANK to the player
	 */
	public Area getClosest(boolean depositBox)
	{
		Collections.sort(BANK_LOCATIONS, bankComparator); //Sort cached banks list using comparator
		for(BankLocation l : BANK_LOCATIONS)
		{
			if(l.isDepositBox() && !depositBox)
				continue;
			
			return l.getArea();
		}
		
		return null;
	}
	
	public Area getClosest()
	{
		return getClosest(true);
	}
	
	
	public boolean isInBank()
	{
		return getClosest(true).contains(myPlayer());
	}
	
	/**
	 * This method returns whether or not the player is in a BANK.
	 * The algorithm used for this is simply grabbing the closest BANK
	 * with getClosest(), and checking if it contains the player
	 * 
	 * @return true if the player is in the BANK, false otherwise
	 */
	public boolean isInBank(boolean depositBox)
	{
		return getClosest(depositBox).contains(myPlayer());
	}
	
	/**
	 * This method opens the closest BANK. The only reason
	 * this method is here is due to the fact that BANK.open()
	 * throws an InterruptedException, so instead of adding
	 * a try / catch block whenever we use it in our code,
	 * we simply make this method and not have to worry about
	 * it again
	 * 
	 * @return whether or not the BANK was opened successfully
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
	 * This is the comparator we use to sort our BANK cache, and
	 * push the best BANK option to the front of the list
	 * 
	 * @return a Comparator which sorts by whether or not the
	 * BANK is valid first, and distance to the player second
	 */
	private Comparator<BankLocation> bankComparator()
	{
		return (one, two) -> {
            final boolean IN_MEMBERS_WORLD = worlds.isMembersWorld();

            //Compare first by whether or not the banks are valid for the character in terms of membership
            if(!IN_MEMBERS_WORLD)
            {
                final int MEM_DIFF = Boolean.compare(one.isMembers(), two.isMembers());
                if(MEM_DIFF != 0)
                    return MEM_DIFF;
            }

            //Compare second by distance to the player
            return myPosition().distance(one.getArea().getRandomPosition()) - myPosition().distance(two.getArea().getRandomPosition());
        };
	}
}
