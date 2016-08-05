package viking.framework.paint.plugin.impl.image;

import java.awt.Point;

import org.osbot.rs07.input.mouse.BotMouseListener;

import viking.framework.paint.VikingPaint;
import viking.framework.script.VikingScript;

public abstract class InteractableImagePlugin extends ImagePlugin implements BotMouseListener
{
	
	public InteractableImagePlugin(VikingScript script, VikingPaint<?> paint,
			String imageUrl, int x, int y)
	{
		super(script, paint, imageUrl, x, y);
		script.bot.addMouseListener(this);
	}

	@Override
	public boolean blockInput(Point p)
	{
		if(image.getImage() == null)
			return false;
	
		script.log(this, true, "blockInput: " + p.getX() + ", " + p.getY());
		return isMouseOnImage();
	}
	
	protected boolean isMouseOnImage()
	{
		return image.getBounds() != null && image.getBounds().contains(script.mouse.getPosition());
	}
}
