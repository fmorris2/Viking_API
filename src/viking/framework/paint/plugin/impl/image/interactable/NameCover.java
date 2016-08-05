package viking.framework.paint.plugin.impl.image.interactable;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.plugin.impl.image.InteractableImagePlugin;
import viking.framework.script.VikingScript;

public class NameCover extends InteractableImagePlugin implements MouseListener, MouseMotionListener
{
	private static final String IMAGE_URL = "/script/paint/_default/name_cover.png";
	private static final int X = 7;
	private static final int Y = 459;
	
	private boolean isHiding = true; //whether or not the name cover is visible
	
	public NameCover(VikingScript script, VikingPaint<?> paint)
	{
		super(script, paint, script.getVikingWebsite() + IMAGE_URL, X, Y);
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		if(isMouseOnImage)
			alpha = isHiding ? 0.33F : 0.66F;
		else
			alpha = isHiding ? 1.00F : 0.00F;
		
		super.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		script.log(this, true, "Mouse clicked at " + e.getX() + ", " + e.getY());
		if(isMouseOnImage)
			isHiding = !isHiding;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
