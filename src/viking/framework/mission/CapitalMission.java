package viking.framework.mission;

/**
 * This interface will represent a mission which requires money in order
 * to run. However if it doesn't have enough money, it will add some other
 * money-making missions to get the requried capital
 * 
 * @author Rick
 *
 */
public interface CapitalMission
{
	public boolean needsCapital();
}
