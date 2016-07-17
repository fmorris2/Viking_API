package viking.api.banking;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

/**
 * This is essentially a wrapper enum for the provided Bank constants in the
 * API. This enum allows for us to do things such as find the closest bank 
 * to our character, and add other filtering options such as whether or not
 * the bank is members only.
 * 
 * @author The Viking
 *
 */
enum VikingBank
{
	AL_KHARID(Banks.AL_KHARID, false),
	ARDOUGNE_NORTH(Banks.ARDOUGNE_NORTH, true),
	ARDOUGNE_SOUTH(Banks.ARDOUGNE_SOUTH, true),
	CAMELOT(Banks.CAMELOT, true),
	CANIFIS(Banks.CANIFIS, true),
	CASTLE_WARS(Banks.CASTLE_WARS, true),
	CATHERBY(Banks.CATHERBY, true),
	DRAYNOR(Banks.DRAYNOR, false),
	DUEL_ARENA(Banks.DUEL_ARENA, false),
	EDGEVILLE(Banks.EDGEVILLE, false),
	FALADOR_EAST(Banks.FALADOR_EAST, false),
	FALADOR_WEST(Banks.FALADOR_WEST, false),
	GNOME_STRONGHOLD(Banks.GNOME_STRONGHOLD, true),
	LUMBRIDGE_LOWER(Banks.LUMBRIDGE_LOWER, true),
	LUMBRIDGE_UPPER(Banks.LUMBRIDGE_UPPER, false),
	PEST_CONTROL(Banks.PEST_CONTROL, true),
	TZHAAR(Banks.TZHAAR, true),
	VARROCK_EAST(Banks.VARROCK_EAST, false),
	VARROCK_WEST(Banks.VARROCK_WEST, false),
	YANILLE(Banks.YANILLE, true);
	
	private Area area;
	private boolean isMembers;
	
	VikingBank(Area area, boolean isMembers)
	{
		this.area = area;
		this.isMembers = isMembers;
	}
	
	//Getters & Setters
	public Area getArea()
	{
		return area;
	}
	
	public boolean isMembers()
	{
		return isMembers;
	}
}
