package viking.framework.paint.mouse;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

import viking.api.Timing;

public class VikingTrailElement
{
	private final static long TIME_TO_LIVE = 2500; //How long the bubble stays alive for
	private final static double STARTING_SIZE_PX = 7;
	private final static double OFFSET = STARTING_SIZE_PX / 2;
	private final static float STARTING_ALPHA = 0.7F;

	private Ellipse2D.Double shape;
	private long startTime;
	private float alpha;
	
	public VikingTrailElement(Point p)
	{
		shape = new Ellipse2D.Double(p.x - OFFSET, p.y - OFFSET, STARTING_SIZE_PX, STARTING_SIZE_PX);
		startTime = Timing.currentMs();
		alpha = STARTING_ALPHA;
	}
	
	public boolean process(Graphics2D g)
	{
		if(Timing.timeFromMark(startTime) > TIME_TO_LIVE) //this element is done with its cycle
			return true;
		
		//modify next size / alpha
		alpha = calculateAlpha();
		shape.height = calculateSize();
		shape.width = shape.height;
		
		//draw shape
		Composite oldComp = g.getComposite();
		Composite newComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		
		g.setComposite(newComp);
		g.setColor(Color.GREEN);
		g.fill(shape);
		g.setComposite(oldComp);
		
		return false;
	}
	
	private float calculateAlpha()
	{
		return getTimeLeft() * STARTING_ALPHA / TIME_TO_LIVE;
	}
	
	private double calculateSize()
	{
		return getTimeLeft() * STARTING_SIZE_PX / TIME_TO_LIVE; 
	}
	
	private long getTimeLeft()
	{
		return TIME_TO_LIVE - Timing.timeFromMark(startTime);
	}
}
