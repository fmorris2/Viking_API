package viking.framework.paint.mouse;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import viking.framework.script.VikingScript;

public class VikingTrailThread extends Thread
{
	private static final int MAX_SCREEN_X = 765;
	private static final int MAX_SCREEN_Y = 503;
	private static final int CYCLE_TIME = 3;
	
	private VikingScript script;
	
	private List<VikingTrailElement> trail;
	private Point lastPoint;
	
	public VikingTrailThread(VikingScript script)
	{
		this.script = script;
		trail = new ArrayList<>();
	}
	
	public void run()
	{
		while(script.bot.getScriptExecutor().isRunning())
		{
			try
			{
				processNewMovement(script.mouse.getPosition());
				sleep(CYCLE_TIME);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
				script.log(this, true, "Thread interrupted");
			}
		}
	}
	
	
	private void processNewMovement(Point p)
	{
		if(lastPoint != null && p.equals(lastPoint)) //mouse idle check
			return;
		
		if(p.x < 0 || p.y < 0 || p.x > MAX_SCREEN_X || p.y > MAX_SCREEN_Y) //mouse out of bounds check
			return;
		
		trail.add(new VikingTrailElement(script, p));
		
		lastPoint = p;
	}
	
	
	//Getters & setters
	public List<VikingTrailElement> getTrail()
	{
		return trail;
	}
}
