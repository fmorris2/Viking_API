package viking.framework.paint.plugin.impl.advanced_paints;

import java.awt.Graphics2D;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.image.VImage;
import viking.framework.paint.plugin.VikingPaintPlugin;
import viking.framework.script.VikingScript;

public abstract class VChatBoxMinimal extends VikingPaintPlugin
{
	public static final int CONTAINER_X = 300;
	public static final int CONTAINER_Y = 345;
	
	private static final String CONTAINER_URL = "/script/paint/_default/main_container_bg.png";
	
	protected VTabBar tabs;
	private VImage containerImage;
	
	public VChatBoxMinimal(VikingScript script, VikingPaint<?> paint)
	{
		super(script, paint);
		tabs = createTabs();
		containerImage = new VImage(script.getVikingWebsite() + CONTAINER_URL);
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		script.assertion(tabs != null, "ChatBox paint should not have null tabs List");
		
		containerImage.draw(g, CONTAINER_X, CONTAINER_Y);	
	}
	
	protected abstract VTabBar createTabs();
}
