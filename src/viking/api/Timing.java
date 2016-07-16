package viking.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Static utility class with various methods that are related
 * to time / timing.
 * 
 * @author The Viking
 *
 */
public class Timing
{
	/**
	 * Calculates the time, in ms, from a specific mark
	 * 
	 * @param mark The initial time mark we're calculating from
	 * @return The time, in ms, from the provided mark
	 */
	public static long timeFromMark(long mark)
	{
		System.out.println("Testy");
		return System.currentTimeMillis() - mark;
	}
	
	/**
	 * Returns the current time in ms. Essentially just a shorter
	 * wrapper for System.currentTimeMillis()
	 * 
	 * @return The current time, in ms
	 */
	public static long currentMs()
	{
		return System.currentTimeMillis();
	}
	
	/**
	 * Converts a time, in ms, to a pretty String in hh:mm:ss:SSS format
	 * 
	 * @param ms The time, in ms, to convert
	 * @return A string representing the current time
	 */
	public static String msToString(long ms)
	{
		Date date = new Date(ms);
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
		return formatter.format(date);
	}
}
