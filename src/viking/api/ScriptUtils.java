package viking.api;

/**
 * This is class which contains static utility methods
 * 
 * @author The Viking
 */
public class ScriptUtils
{
	/**
	 * Utility method to log a message to the console, with the class name from where it is called displayed along with it
	 * 
	 * @param c The object from which we're calling this method
	 * @param message The message to log
	 */
	public static void log(Object c, String message)
	{
		System.out.println(c.getClass().getSimpleName() + ": " + message);
	}
}
