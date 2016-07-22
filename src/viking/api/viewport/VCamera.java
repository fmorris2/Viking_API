package viking.api.viewport;

import viking.api.ScriptUtil;
import viking.framework.script.VikingScript;

/**
 * VCamera is the object that all Viking scripts use
 * to turn the camera - asyncrhonously. Just as a human,
 * Viking scripts can turn the camera at the same time as
 * moving the mouse, clicking, or typing with the keyboard
 * 
 * @author The Viking
 *
 */
public class VCamera extends ScriptUtil implements Runnable
{
	private boolean isRunning = true;
	
	public VCamera(VikingScript script)
	{
		super(script);
	}

	@Override
	public void run()
	{
		while(isRunning)
		{			
			script.log(this, true, "Is running..");
			waitForChange();
		}
		
		script.log(this, true, "ended...");
	}
	
	public synchronized boolean toTop()
	{
		//notifyAll();
		return camera.toTop();
	}
	
	public synchronized void stop()
	{
		isRunning = false;
		notifyAll();
	}
	
	private synchronized void waitForChange()
	{
		try
		{
			wait();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
			script.log(this, true, "Interrupted exception in waitForChange()");
		}
	}

}
