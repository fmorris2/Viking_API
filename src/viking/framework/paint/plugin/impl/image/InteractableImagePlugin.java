package viking.framework.paint.plugin.impl.image;

import java.awt.event.MouseListener;

import viking.framework.paint.VikingPaint;
import viking.framework.script.VikingScript;

public abstract class InteractableImagePlugin extends ImagePlugin implements MouseListener
{
	
	public InteractableImagePlugin(VikingScript script, VikingPaint<?> paint,
			String imageUrl, int x, int y)
	{
		super(script, paint, imageUrl, x, y);
		script.bot.addMouseListener(this);
	}
	
	protected boolean isMouseOnImage()
	{
		return image.getBounds() != null && image.getBounds().contains(script.mouse.getPosition());
	}
}
