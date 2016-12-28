package viking.api;

import java.util.concurrent.TimeUnit;

import org.osbot.rs07.utility.ConditionalSleep;

import viking.api.condition.VCondition;

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
		//test
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
		return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(ms),
			    TimeUnit.MILLISECONDS.toMinutes(ms) % TimeUnit.HOURS.toMinutes(1),
			    TimeUnit.MILLISECONDS.toSeconds(ms) % TimeUnit.MINUTES.toSeconds(1));
	}
	
	/**
	 * This method waits for a specific condition
	 * to be true within a maximum amount of time. Your
	 * basic conditional sleep.
	 * 
	 * @param condition the condition to wait for
	 * @param cycleTime the time, in ms, between condition checks
	 * @param timeout the maximum time to wait for the condition to be true
	 * @return true if the condition was met within the threshold, or false if the timeout was exceeded
	 * @throws InterruptedException
	 */
	public static boolean waitCondition(VCondition condition, int cycleTime, int timeout)
	{
		return new ConditionalSleep(timeout, cycleTime)
		{
			@Override
			public boolean condition() throws InterruptedException
			{
				return condition.evaluate();
			}
			
		}.sleep();
	}

}
