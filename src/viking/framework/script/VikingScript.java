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
	
	/**
	 * Primary VikingScript constructor which takes in the essential
	 * parameters to run a VikingScript.
	 * 
	 * @param vikingPaint the VikingPaint object which will drive the paint system for this script
	 */
	public VikingScript()
	{
		vikingPaint = getVikingPaint();
		missionHandler = new MissionHandler(this, generateMissions());
	}
	
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
	 * Utility method to log a message to the console, with the class name from where it is called displayed along with it
	 * 
	 * @param c The object from which we're calling this method
	 * @param message The message to log
	 */
	public void log(Object c, String message)
	{
		log("["+c.getClass().getSimpleName()+"]" + ": " + message);
	}
	
	@Override
	public int onLoop()
	{
		return missionHandler.execute();
	}

	@Override
	public void onStart()
	{
		log(this, "Started " + getName() + " v" + getVersion() + " by " + getAuthor());
	}

	@Override
	public void onExit()
	{
		log(this, "Ended " + getName() + " v" + getVersion() + " by " + getAuthor());
	}

	@Override
	public void onPaint(Graphics2D g)
	{
		vikingPaint.paint(g);
	}

}
