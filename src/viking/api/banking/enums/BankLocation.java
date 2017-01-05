package viking.api.banking.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

import java.util.Arrays;

/**
 * This is essentially a wrapper enum for the provided Bank constants in the
 * API. This enum allows for us to do things such as find the closest bank
 * to our character, and add other filtering options such as whether or not
 * the bank is members only.
 *
 * @author The Viking
 */
public enum BankLocation {

    YANILLE(Banks.YANILLE, true),
    LUMBRIDGE_UPPER(Banks.LUMBRIDGE_UPPER, false),
    TZHAAR(Banks.TZHAAR, true),
    LOVAKENGJ_HOUSE(Banks.LOVAKENGJ_HOUSE, true),
    VARROCK_WEST(Banks.VARROCK_WEST, false),
    DUEL_ARENA(Banks.DUEL_ARENA, false),
    AL_KHARID(Banks.AL_KHARID, false),
    CASTLE_WARS(Banks.CASTLE_WARS, true),
    EDGEVILLE(Banks.EDGEVILLE, false),
    FALADOR_EAST(Banks.FALADOR_EAST, false),
    ARDOUGNE_NORTH(Banks.ARDOUGNE_NORTH, true),
    CATHERBY(Banks.CATHERBY, true),
    PEST_CONTROL(Banks.PEST_CONTROL, true),
    FALADOR_WEST(Banks.FALADOR_WEST, false),
    GRAND_EXCHANGE(Banks.GRAND_EXCHANGE, false),
    HOSIDIUS_HOUSE(Banks.HOSIDIUS_HOUSE, true),
    SHAYZIEN_HOUSE(Banks.SHAYZIEN_HOUSE, true),
    ARCEUUS_HOUSE(Banks.ARCEUUS_HOUSE, true),
    GNOME_STRONGHOLD(Banks.GNOME_STRONGHOLD, true),
    ARDOUGNE_SOUTH(Banks.ARDOUGNE_SOUTH, true),
    VARROCK_EAST(Banks.VARROCK_EAST, false),
    LUMBRIDGE_LOWER(Banks.LUMBRIDGE_LOWER, true),
    DRAYNOR(Banks.DRAYNOR, false),
    CAMELOT(Banks.CAMELOT, true),
    CANIFIS(Banks.CANIFIS, true),
    PISCARILIUS_HOUSE(Banks.PISCARILIUS_HOUSE, true),
    LOVAKITE_MINE(Banks.LOVAKITE_MINE, true);

    private Area area;
    private boolean isMembers;

    BankLocation(Area area, boolean isMembers) {
        this.area = area;
        this.isMembers = isMembers;
    }

    //Getters & Setters
    public Area getArea() {
        return area;
    }

    public boolean isMembers() {
        return isMembers;
    }

}
