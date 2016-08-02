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
	private final static int COLOR_CHANGES = 25;
	
	private final static Color ORANGE_RED = new Color(237, 61, 0);
	private final static long COLOR_CHANGE_TIME = TIME_TO_LIVE / COLOR_CHANGES;
	

	private Ellipse2D.Double shape;
	private long startTime;
	private long lastColorChange;
	private float alpha;
	private Color color;
	
	public VikingTrailElement(Point p)
	{
		shape = new Ellipse2D.Double(p.x - OFFSET, p.y - OFFSET, STARTING_SIZE_PX, STARTING_SIZE_PX);
		startTime = Timing.currentMs();
		alpha = STARTING_ALPHA;
		color = new Color(ORANGE_RED.getRGB());
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
		g.setColor(color);
		g.fill(shape);
		g.setComposite(oldComp);
		
		//modify color
		if(Timing.timeFromMark(lastColorChange) >= COLOR_CHANGE_TIME)
		{
			color = color.darker();
			lastColorChange = Timing.currentMs();
		}
		
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
