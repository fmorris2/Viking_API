package viking.framework.script;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.Script;

import viking.framework.bank_cache.BankCache;
import viking.framework.item_management.IMEntry;
import viking.framework.item_management.ItemManagement;
import viking.framework.item_management.ItemManagementEvent;
import viking.framework.item_management.ItemManagementTracker;
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
	private static final String DEVELOPMENT_SITE = "http://dev.vikingscripts.io";
	private static final String PRODUCTION_SITE = "http://vikingscripts.io";
	
	public final Map<String, String> PARAMS = new HashMap<>();
	
	public BankCache BANK_CACHE;
	private VikingPaint<?> vikingPaint; //VikingPaint system that will handle all of the painting
	private MissionHandler missionHandler; //Handles / drives the missions for the script
	private ScriptUtils utils; //Holds the various script utilities for each Viking Script
	private BlockingQueue<Message> messageQueue = new ArrayBlockingQueue<>(100);
	private ItemManagementTracker imTracker;
	
	
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
	public abstract boolean isDevBuild();
	
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
	
	private void sendMessageUpdates()
	{
		try
		{	
			Mission current = missionHandler.getCurrent();
			if(current != null)
			{
				for(Message m : messageQueue)
					current.onMessage(m);
				
				messageQueue.clear();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public int onLoop()
	{
		try
		{
			//send message updates to missions
			sendMessageUpdates();
			
			//check for item management system
			IMEntry toBuy = needsItemManagement();
			if(toBuy != null)
				handleImEvent(toBuy);
			else
				return missionHandler.execute();
		}
		catch(Exception e)
		{
			log(e.getClass().getName());
			StackTraceElement[] elements = e.getStackTrace();
			for(StackTraceElement st : elements)
				log(st.toString());
				
			stop();
		}
		
		return 400;
	}

	@Override
	public void onStart()
	{
		log(this, false, "Started " + getName() + " v" + getVersion() + " by " + getAuthor());
		bot.addMessageListener(this);
		utils = new ScriptUtils();
		utils.init(this);
		parseParams();
		missionHandler = new MissionHandler(this, generateMissions());
		vikingPaint = getVikingPaint();
		BANK_CACHE = new BankCache(this);
		BANK_CACHE.start();
	}
	
	private void handleImEvent(IMEntry toBuy)
	{
		log(this, false, "Item Management needs to buy " + toBuy);
		ItemManagementEvent imEvent = new ItemManagementEvent(this, missionHandler.getCurrent(), toBuy, imTracker);
		while(!imEvent.isFinished())
		{
			imEvent.execute();
			missionHandler.getCurrent().waitMs(400);
		}
		
	}
	
	private IMEntry needsItemManagement()
	{
		Mission current = missionHandler.getCurrent();
		if(current instanceof ItemManagement)
		{
			//apply tracker if necessary
			if(imTracker == null || imTracker.IM != current)
				imTracker = new ItemManagementTracker(this, (ItemManagement)current);
			
			//update tracker info
			imTracker.update();
			
			//see if we need to initiate item management
			return imTracker.needsToBuy();
		}
		
		return null;
	}
	
	private void parseParams()
	{
		if(getParameters() != null)
		{
			log(this, false, "Parsing parameters from String " + getParameters());
			String[] parts = getParameters().split("\\.");
			for(String part : parts)
			{
				String[] keyVal = part.split(";");
				log(this, false, "Params now contains key " + keyVal[0] + " with val " + keyVal[1]);
				PARAMS.put(keyVal[0], keyVal[1]);
			}
		}
	}

	@Override
	public void onExit()
	{
		log(this, false, "Ended " + getName() + " v" + getVersion() + " by " + getAuthor());
	}

	@Override
	public void onPaint(Graphics2D g)
	{
		if(vikingPaint != null)
			vikingPaint.paint(g);
	}
	
	@Override
	public void onMessage(Message m)
	{
		messageQueue.add(m);
	}
	
	//Gettters
	public ScriptUtils getUtils()
	{
		return utils;
	}
	
	public MissionHandler getMissionHandler()
	{
		return missionHandler;
	}
	
	public String getVikingWebsite()
	{
		return isDevBuild() ? DEVELOPMENT_SITE : PRODUCTION_SITE;
	}
}
