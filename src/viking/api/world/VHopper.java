package viking.api.world;

import java.awt.Rectangle;
import java.util.HashMap;

import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.input.mouse.RectangleDestination;

import viking.api.Timing;
import viking.framework.VMethodProvider;

/**
 * FCInGameHopper
 * 
 * Use as so: FCInGameHopper.hop(worldNumber) (For example world 301 would be world 1, 381 would be 81 and so on)
 * Lack of null checking is due to the "Easier to Ask Forgiveness than Permission" (EAFP) programming methodology used here
 * Link to explanation: http://docstore.mik.ua/orelly/other/python/0596001886_pythonian-chp-6-sect-6.html
 * This link is specific to python, but also can apply to Java
 * 
 * @author Final Calibur with inspiration from daxmagex
 */
public class VHopper extends VMethodProvider
{
	//constants
	
	//cache so we can just pull the appropriate child id for a world instead of looking for it every time
	private static final HashMap<Integer, Integer> WORLD_CHILDREN_CACHE = new HashMap<>();
	
	//normal logout inter constants
	private static final int NORMAL_LOGOUT_MASTER = 182;
	private static final int WORLD_SWITCHER_CHILD = 1;
	
	//world hopper inter constants
	private static final int HOPPER_MASTER = 69;
	private static final int HOPPER_CLOSE_CHILD = 3;
	private static final int HOPPER_LIST_CHILD = 7;
	private static final int MIN_Y = 228;
	private static final int MAX_Y = 418;
	
	//misc. constants
	private static final int INTERFACE_LOAD_TIMEOUT = 5000; //max wait time for world hopper inter to load

	//public API methods
	public boolean hop(int world)
	{
		if(world > 100)
			world %= 100;
		
		//open hopper interface if necessary
		if(tabs.open(Tab.LOGOUT) && (isHopperUp() || openHopper()))
		{
			try
			{
				final RS2Widget WORLD_LIST = widgets.get(HOPPER_MASTER, HOPPER_LIST_CHILD);
				final Integer WORLD_CHILD = WORLD_CHILDREN_CACHE.get(world);
				
				//load cache if necessary
				if((WORLD_CHILDREN_CACHE.isEmpty() || WORLD_CHILD == null) && WORLD_LIST != null)
					loadCache(WORLD_LIST);
				
				final RS2Widget TARGET_WORLD = WORLD_LIST.getChildWidget(WORLD_CHILDREN_CACHE.get(world));
		
				//check if world is scrolled into view
				if(isWorldVisible(TARGET_WORLD) || scrollWorldIntoView(WORLD_LIST, TARGET_WORLD))
					return clickWorld(WORLD_LIST.getChildWidget(WORLD_CHILDREN_CACHE.get(world)), world) //Can't use cached TARGET_WORLD due to it's pos changing on scroll
							&& Timing.waitCondition(() -> client.isLoggedIn(), 6000);
			}
			catch(Exception e){}
		}
			
		return false;
	}
	
	//utility methods	
	public boolean isHopperUp()
	{
		return widgets.get(HOPPER_MASTER, HOPPER_LIST_CHILD) != null;
	}
	
	private boolean openHopper()
	{
		final RS2Widget WORLD_SWITCHER_BUTTON = widgets.get(NORMAL_LOGOUT_MASTER, WORLD_SWITCHER_CHILD);
		
		if(WORLD_SWITCHER_BUTTON != null && WORLD_SWITCHER_BUTTON.interact())
			return Timing.waitCondition(() -> isHopperUp(), INTERFACE_LOAD_TIMEOUT) &&
					Timing.waitCondition(() -> widgets.getWidgetContainingText("Loading...") != null, INTERFACE_LOAD_TIMEOUT);
		
		return false;
	}
	
	public boolean closeHopper()
	{
		final RS2Widget CLOSE_BUTTON = widgets.get(HOPPER_MASTER, HOPPER_CLOSE_CHILD);
		
		if(CLOSE_BUTTON != null && CLOSE_BUTTON.interact())
			return Timing.waitCondition(() -> isHopperUp(), INTERFACE_LOAD_TIMEOUT);
		
		return false;
	}
	
	private boolean isWorldVisible(RS2Widget targetWorld)
	{	
		Rectangle rect = targetWorld.getBounds();
		
		return rect.y > MIN_Y && rect.y < MAX_Y;
	}
	
	private boolean scrollWorldIntoView(RS2Widget worldList, RS2Widget targetWorld)
	{
		final long START_TIME = Timing.currentMs();
		final long TIMEOUT = 7000;
		final Rectangle WORLD_LIST_BOUNDS = worldList.getBounds();
		
		Rectangle targetRectangle;
		
		do
		{
			//move mouse into world list interface if necessary
			if(!WORLD_LIST_BOUNDS.contains(mouse.getPosition()))
				mouse.move(new RectangleDestination(bot, WORLD_LIST_BOUNDS));
			
			//scroll in appropriate direction
			targetRectangle = targetWorld.getBounds();
			if(targetRectangle.y < MIN_Y)
				mouse.scrollUp();
			else
				mouse.scrollDown();
			
			if(Timing.timeFromMark(START_TIME) > TIMEOUT)
				return false;                  
			waitMs(random(10, 40));
		}
		while(!isWorldVisible(targetWorld));
		
		waitMs(random(70, 120));
		return true;
	}
	
	private boolean clickWorld(RS2Widget targetWorld, int worldNum)
	{
		if(targetWorld.hover())
		{
			targetWorld.interact();
			if(Timing.waitCondition(() -> dialogues.isPendingOption(), 1500))
				dialogues.selectOption(2);
			
			return Timing.waitCondition(() -> worlds.getCurrentWorld() == worldNum, 5000);
		}
		
		return false;
	}
	
	private void loadCache(RS2Widget worldList)
	{	
		final int FIRST_WORLD_COMPONENT = 0;
		final int WORLD_NUMBER_OFFSET = 2;
		final int WORLD_OFFSET = 6;
		
		for(int i = FIRST_WORLD_COMPONENT; i < worldList.getChildWidgets().length; i += WORLD_OFFSET)
		{
			RS2Widget mainChild = worldList.getChildWidget(i);
			RS2Widget worldNumChild = worldList.getChildWidget(i + WORLD_NUMBER_OFFSET);
			
			if(mainChild != null && worldNumChild != null)
				WORLD_CHILDREN_CACHE.put(Integer.parseInt(worldNumChild.getMessage()), i);
		}		
	}
}