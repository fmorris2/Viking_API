package viking_api.api.location;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

/**
 * The Location class represents any location in the game that the
 * character will travel to and execute a specific task. For example,
 * this would apply to woodcutting locations, or combat locations.
 * This class includes methods to get to the location and also to go
 * to the bank.
 * 
 * @author The Viking
 *
 */
public abstract class Location
{
	protected Area area; //The area that applies to this location
	protected Position depositBoxTile;
	
	/**
	 * Primary Location constructor. Abstract method from the implementing
	 * classes are used once, in this constructor, and cached in the various
	 * fields in this class.
	 */
	public Location()
	{
		area = constructArea();
		depositBoxTile = constructDepositBoxTile();
	}
	
	/**
	 * Constructs the Area which this location is defined in
	 * 
	 * @return Area object which defines the bounds of this Location
	 */
	public abstract Area constructArea();
	
	/**
	 * Constructs the Position which defines where the deposit box for this Location
	 * exists, if there is one
	 * 
	 * @return Position object which defines where the deposit box is, or null if there isn't one
	 */
	public abstract Position constructDepositBoxTile();
	
	
	//Getters
	/**
	 * Getter for area
	 * 
	 * @return Area object that applies to this location
	 */
	public Area getArea()
	{
		return area;
	}
	
}
