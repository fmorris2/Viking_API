package viking.framework.paint.plugin.impl.image.interactable;

import java.awt.event.MouseEvent;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.plugin.impl.image.InteractableImagePlugin;
import viking.framework.script.VikingScript;

public class NameCover extends InteractableImagePlugin
{
	private static final String IMAGE_URL = "/script/paint/_default/name_cover.png";
	private static final int X = 7;
	private static final int Y = 459;
	
	private boolean isHiding; //whether or not the name cover is visible
	
	public NameCover(VikingScript script, VikingPaint<?> paint)
	{
		super(script, paint, script.getVikingWebsite() + IMAGE_URL, X, Y);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		isHiding = !isHiding;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		alpha = isHiding ? 0.66F : 0.33F;
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		alpha = isHiding ? 1.00F : 0.00F;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
