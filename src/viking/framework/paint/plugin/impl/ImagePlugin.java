package viking.framework.paint.plugin.impl;

import java.awt.Graphics2D;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.image.VImage;
import viking.framework.paint.plugin.VikingPaintPlugin;
import viking.framework.script.VikingScript;

public class ImagePlugin extends VikingPaintPlugin
{
	private int x;
	private int y;
	private VImage image;
	
	public ImagePlugin(VikingScript script, VikingPaint<?> paint, String imageUrl, int x, int y)
	{
		super(script, paint);
		this.x = x;
		this.y = y;
		image = new VImage(imageUrl);
	}

	@Override
	public void draw(Graphics2D g)
	{
		if(image.getImage() != null)
			g.drawImage(image.getImage(), x, y, null);
	}

	@Override
	public void reset()
	{}

}
