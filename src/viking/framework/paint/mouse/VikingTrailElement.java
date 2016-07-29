package viking.framework.paint.mouse;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import viking.api.Timing;

public class VikingTrailElement
{
	private final static int TIME_TO_LIVE = 2500; //How long the bubble stays alive for
	private final static int FADE_INTERVAL = 100; //Time between fade cycles
	private final static int ALPHA_FADE_AMT = 100 / (TIME_TO_LIVE / FADE_INTERVAL); //how much the alpha decreases by each fade cycle
	private final static double STARTING_SIZE_PX = 8;
	private final static double SIZE_FADE_AMT = STARTING_SIZE_PX / (TIME_TO_LIVE / FADE_INTERVAL); //how much the size decreaces by each fade cycle
	
	private Ellipse2D.Double shape;
	private long startTime;
	private long lastCycle;
	private int alpha;
	
	public VikingTrailElement(int x, int y)
	{
		shape = new Ellipse2D.Double(x, y, STARTING_SIZE_PX, STARTING_SIZE_PX);
		startTime = Timing.currentMs();
		alpha = 100;
	}
	
	public boolean process(Graphics2D g)
	{
		if(Timing.timeFromMark(startTime) > TIME_TO_LIVE) //this element is done with its cycle
			return true;
		
		if(Timing.timeFromMark(lastCycle) >= FADE_INTERVAL)
		{
			//draw shape
			Composite oldComp = g.getComposite();
			Composite newComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			
			g.setComposite(newComp);
			g.fill(shape);
			g.setComposite(oldComp);
			
			//modify next size / alpha
			alpha -= ALPHA_FADE_AMT;
			shape.height -= SIZE_FADE_AMT;
			shape.width -= SIZE_FADE_AMT;
			
			lastCycle = Timing.currentMs();
		}
		
		return false;
	}
}
