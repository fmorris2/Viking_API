package viking.framework.paint.plugin.impl.advanced_paints;

import java.awt.Graphics2D;
import java.util.List;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.image.VImage;
import viking.framework.paint.plugin.VikingPaintPlugin;
import viking.framework.script.VikingScript;

public abstract class VChatBoxMinimal extends VikingPaintPlugin
{
	private static final String CONTAINER_URL = "/script/paint/_default/main_container_bg.png";
	private static final int CONTAINER_X = 300;
	private static final int CONTAINER_Y = 345;
	
	protected List<VPaintTab> tabs;
	private VImage containerImage;
	
	public VChatBoxMinimal(VikingScript script, VikingPaint<?> paint)
	{
		super(script, paint);
		tabs = createTabs();
		containerImage = new VImage(CONTAINER_URL);
	}
	
	public void handle(Graphics2D g)
	{
		super.handle(g);
		
		if(isVisible)
			containerImage.draw(g, CONTAINER_X, CONTAINER_Y);
			
	}
	
	protected abstract List<VPaintTab> createTabs();
}
