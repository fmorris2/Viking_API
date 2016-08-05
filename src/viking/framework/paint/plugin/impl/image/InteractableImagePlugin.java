package viking.framework.paint.plugin.impl.image;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import viking.framework.paint.VikingPaint;
import viking.framework.script.VikingScript;

public abstract class InteractableImagePlugin extends ImagePlugin implements MouseListener, MouseMotionListener
{
	protected boolean isMouseOnImage;
	
	public InteractableImagePlugin(VikingScript script, VikingPaint<?> paint,
			String imageUrl, int x, int y)
	{
		super(script, paint, imageUrl, x, y);
		script.bot.addMouseListener(this);
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{		
		if(image.getBounds() != null && image.getBounds().contains(e.getPoint()))
			isMouseOnImage = true;
		else
			isMouseOnImage = false;
	}
	
	@Override
	public void mouseDragged(MouseEvent e){}
}
