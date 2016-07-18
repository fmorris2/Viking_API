package viking.framework.script;

import java.awt.Graphics2D;
import java.util.Queue;

import org.osbot.rs07.script.Script;

import viking.framework.mission.Mission;
import viking.framework.mission.MissionHandler;
import viking.framework.paint.VikingPaint;

/**
 * This is the base class that all Viking scripts will extend from. The purpose
 * of this class is to handle redundant things that are done from every script
 * class, such as start & end messages, and painting
 * 
 * @author The Viking
 *
 */
public abstract class VikingScript extends Script
{
	private VikingPaint<?> vikingPaint; //VikingPaint system that will handle all of the painting
	private MissionHandler missionHandler; //Handles / drives the missions for the script
	private ScriptUtils utils; //Holds the various script utilities for each Viking Script
	
	/**
	 * This method will be provided by the script implementation.
	 * The purpose of this is so that each script can define a
	 * queue of missions that it will execute, thus allowing
	 * for a greater level of flexibility on the framework.
	 * <p>
	 * For example, scripts will be able to chain together multiple
	 * unrelated missions, this is especially useful when it comes to
	 * a quest script, or any other script will multiple tasks.
	 * 
	 * @return an initial queue of missions for the script to execute
	 */
	public abstract Queue<Mission> generateMissions();
	
	/**
	 * This method will be provided by the script implementation.
	 * Each script will construct their own VikingPaint object,
	 * which will be used to handle the paint
	 * 
	 * @return VikingPaint object used to drive the painting for the script
	 */
	public abstract VikingPaint<?> getVikingPaint();
	
	/**
	 * This method defines whether or not the current Script is in the testing
	 * phase
	 * 
	 * @return true if the script is in the testing phase, false otherwise
	 */
	protected abstract boolean isDevBuild();
	
	/**
	 * Utility method to log a message to the console, with the class name from where it is called displayed along with it
	 * 
	 * @param c the object from which we're calling this method
	 * @param debug if this is a message specifically for testing builds of the script
	 * @param message the message to log
	 */
	public void log(Object c, boolean debug, String message)
	{
		if(!debug || isDevBuild())
			log("["+c.getClass().getSimpleName()+"]" + ": " + message);
	}
	
	/**
	 * Due to the assert statement not currently working with OSBot, I have to make my own.
	 * This method will stop the script if the condition is false
	 * 
	 * @param condition condition to check
	 * @param msg message to send if condition fails
	 */
	public void assertion(boolean condition, String msg)
	{
		if(!condition)
		{
			log(this, true, "[ASSERTION] " + msg);
			stop();
		}
	}
	
	@Override
	public int onLoop()
	{
		return missionHandler.execute();
	}

	@Override
	public void onStart()
	{
		log(this, false, "Started " + getName() + " v" + getVersion() + " by " + getAuthor());
		utils = new ScriptUtils(this);
		missionHandler = new MissionHandler(this, generateMissions());
		vikingPaint = getVikingPaint();
	}

	@Override
	public void onExit()
	{
		log(this, false, "Ended " + getName() + " v" + getVersion() + " by " + getAuthor());
	}

	@Override
	public void onPaint(Graphics2D g)
	{
		vikingPaint.paint(g);
	}
	
	//Gettters
	public ScriptUtils getUtils()
	{
		return utils;
	}
}
