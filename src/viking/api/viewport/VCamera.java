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
	public VCamera(VikingScript script)
	{
		super(script);
	}

	@Override
	public void run()
	{
		while(script != null)
		{
			script.log(this, true, "Is running..");
			
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
