package viking.framework.paint.mouse;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import viking.api.Timing;
import viking.framework.script.VikingScript;

public class VikingMouseTrail
{
	private static final int CYCLE_TIME = 200; //time between drawing new trail elements
	
	private List<VikingTrailElement> trail;
	
	private VikingScript script;
	private Point lastPoint;
	private long lastCycle;
	
	public VikingMouseTrail(VikingScript script)
	{
		this.script = script;
		trail = new ArrayList<>();
	}
	
	public void processNewMovement(Point p)
	{
		if(Timing.timeFromMark(lastCycle) < CYCLE_TIME || (lastPoint != null && p.equals(lastPoint))) //cycle time && mouse idle check
			return;
		
		if(p.x < 0 || p.y < 0) //mouse out of bounds check
			return;
		
		script.log(this, true, "Adding new TrailElement for Point " + p);
		
		trail.add(new VikingTrailElement(script, p));
		
		lastPoint = p;
		lastCycle = Timing.currentMs();
	}
	
	public void processTrail(Graphics2D g)
	{
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
