package viking.framework.paint.plugin.impl.image;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.image.VImage;
import viking.framework.paint.plugin.VikingPaintPlugin;
import viking.framework.script.VikingScript;

public class ImagePlugin extends VikingPaintPlugin
{
	private int x;
	private int y;
	protected VImage image;
	private Composite alphaComposite;
	
	protected float alpha = 1.0F;
	
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
		Composite oldComposite = g.getComposite();
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		
		g.setComposite(alphaComposite);
		
		if(image.getImage() != null)
			g.drawImage(image.getImage(), x, y, null);
		
		g.setComposite(oldComposite);
	}

	@Override
	public void reset()
	{}

}
