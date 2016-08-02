package viking.framework.paint.mouse;

import java.awt.Graphics2D;
import java.util.List;

import viking.framework.script.VikingScript;

public class VikingMouseTrail
{	
	private VikingTrailThread trailThread;
	
	public VikingMouseTrail(VikingScript script)
	{
		trailThread = new VikingTrailThread(script);
	}
	
	public void processTrail(Graphics2D g)
	{
		List<VikingTrailElement> trail = trailThread.getTrail();
		
		if(trail.isEmpty())
			return;
		
		for(int i = trail.size() - 1; i >= 0; i--)
		{
			VikingTrailElement e = trail.get(i);
			
			if(e.process(g))
				trail.remove(i);
		}
	}
}
