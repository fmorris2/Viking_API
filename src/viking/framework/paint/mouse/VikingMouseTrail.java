package viking.framework.paint.mouse;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class VikingMouseTrail
{
	private static final int MAX_SCREEN_X = 765;
	private static final int MAX_SCREEN_Y = 503;
	
	private List<VikingTrailElement> trail;
	
	private Point lastPoint;
	
	public VikingMouseTrail()
	{
		trail = new ArrayList<>();
	}
	
	public void processNewMovement(Point p)
	{
		if(lastPoint != null && p.equals(lastPoint)) //mouse idle check
			return;
		
		if(p.x < 0 || p.y < 0 || p.x > MAX_SCREEN_X || p.y > MAX_SCREEN_Y) //mouse out of bounds check
			return;
		
		trail.add(new VikingTrailElement(p));
		
		lastPoint = p;
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
