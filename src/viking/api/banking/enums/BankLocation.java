package viking.api.banking.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

/**
 * This is essentially a wrapper enum for the provided Bank constants in the
 * API. This enum allows for us to do things such as find the closest bank
 * to our character, and add other filtering options such as whether or not
 * the bank is members only.
 *
 * @author The Viking
 */
public enum BankLocation {

    YANILLE(Banks.YANILLE, true, false),
    LUMBRIDGE_UPPER(Banks.LUMBRIDGE_UPPER, false, false),
    TZHAAR(Banks.TZHAAR, true, false),
    LOVAKENGJ_HOUSE(Banks.LOVAKENGJ_HOUSE, true, false),
    VARROCK_WEST(Banks.VARROCK_WEST, false, false),
    DUEL_ARENA(Banks.DUEL_ARENA, false, false),
    AL_KHARID(Banks.AL_KHARID, false, false),
    CASTLE_WARS(Banks.CASTLE_WARS, true, false),
    EDGEVILLE(Banks.EDGEVILLE, false, false),
    FALADOR_EAST(Banks.FALADOR_EAST, false, false),
    ARDOUGNE_NORTH(Banks.ARDOUGNE_NORTH, true, false),
    CATHERBY(Banks.CATHERBY, true, false),
    PEST_CONTROL(Banks.PEST_CONTROL, true, false),
    FALADOR_WEST(Banks.FALADOR_WEST, false, false),
    GRAND_EXCHANGE(Banks.GRAND_EXCHANGE, false, false),
    HOSIDIUS_HOUSE(Banks.HOSIDIUS_HOUSE, true, false),
    SHAYZIEN_HOUSE(Banks.SHAYZIEN_HOUSE, true, false),
    ARCEUUS_HOUSE(Banks.ARCEUUS_HOUSE, true, false),
    GNOME_STRONGHOLD(Banks.GNOME_STRONGHOLD, true, false),
    ARDOUGNE_SOUTH(Banks.ARDOUGNE_SOUTH, true, false),
    VARROCK_EAST(Banks.VARROCK_EAST, false, false),
    LUMBRIDGE_LOWER(Banks.LUMBRIDGE_LOWER, true, false),
    DRAYNOR(Banks.DRAYNOR, false, false),
    CAMELOT(Banks.CAMELOT, true, false),
    CANIFIS(Banks.CANIFIS, true, false),
    PISCARILIUS_HOUSE(Banks.PISCARILIUS_HOUSE, true, false),
    LOVAKITE_MINE(Banks.LOVAKITE_MINE, true, false),
    PORT_SARIM_DEPOSIT_BOX(new Area(new int[][]{
            {3041, 3237},
            {3048, 3237},
            {3048, 3229},
            {3041, 3230}
    }), false, true);

    private final Area AREA;
    private final boolean IS_MEMEBERS;
    private final boolean IS_DEPOSIT_BOX;

    BankLocation(Area area, boolean is_members, boolean is_deposit_box) {
        this.AREA = area;
        this.IS_MEMEBERS = is_members;
        this.IS_DEPOSIT_BOX = is_deposit_box;
    }

    //Getters & Setters
    public Area getArea() {
        return AREA;
    }

    public boolean isMembers() {
        return IS_MEMEBERS;
    }

    public boolean isDepositBox() {
        return IS_DEPOSIT_BOX;
    }

}
