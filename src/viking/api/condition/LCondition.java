package viking.api.condition;

/**
 * This interface provides an important function. It allows for lambda usage
 * in Timing.waitCondition
 * 
 * @author Freddy
 *
 */
public interface LCondition
{
	public boolean evaluate();
}
