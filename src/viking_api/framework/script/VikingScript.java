package viking_api.framework.script;

import java.awt.Graphics2D;
import java.util.Queue;

import org.osbot.rs07.script.Script;

import viking_api.framework.mission.Mission;
import viking_api.framework.paint.VikingPaint;

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
	private Mission currentMission; //The current mission we're executing. There can potentially be other missions behind this one, in the queue
	private Queue<Mission> missionQueue; //The mission queue, of missions to execute after the current mission
	
	/**
	 * Primary VikingScript constructor which takes in the essential
	 * parameters to run a VikingScript.
	 * 
	 * @param vikingPaint the VikingPaint object which will drive the paint system for this script
	 */
	public VikingScript()
	{
		vikingPaint = getVikingPaint();
		missionQueue = generateMissions();
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
	 * This method is overridden by the script implementation,
	 * it essentially is the same as onLoop(), except this class
	 * has its own code that is needed in onLoop(), so the scripts
	 * can't simply override it.
	 * 
	 * @return the main logic loop for the script implementation, returns the sleep time for this cycle
	 */
	public abstract int mainLogic();
	
	@Override
	public int onLoop()
	{
		return 1000;
	}

	@Override
	public void onStart()
	{
		log("Started " + getName() + " v" + getVersion() + " by " + getAuthor());
	}

	@Override
	public void onExit()
	{
		log("Ended " + getName() + " v" + getVersion() + " by " + getAuthor());
	}

	@Override
	public void onPaint(Graphics2D g)
	{
		vikingPaint.paint(g);
	}

}
