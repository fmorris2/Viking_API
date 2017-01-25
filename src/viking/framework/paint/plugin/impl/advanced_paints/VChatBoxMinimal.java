package viking.framework.paint.plugin.impl.advanced_paints;

import java.awt.Color;
import java.awt.Graphics2D;

import viking.framework.paint.VikingPaint;
import viking.framework.paint.image.VImage;
import viking.framework.paint.plugin.VikingPaintPlugin;
import viking.framework.script.VikingScript;

public abstract class VChatBoxMinimal extends VikingPaintPlugin
{
	public static final int CONTAINER_X = 300;
	public static final int CONTAINER_Y = 345;
	public static final Color DEFAULT_COLOR = new Color(254, 254, 254); //almost white w/ gray tint
	
	private static final int LOGO_X = 264; 
	private static final int LOGO_Y = 268;
	private static final String CONTAINER_URL = "/script/paint/_default/main_container_bg.png";
	
	protected VTabBar tabs;
	private VImage containerImage;
	private VImage logo;
	
	public VChatBoxMinimal(VikingScript script, VikingPaint<?> paint, String logoUrl)
	{
		super(script, paint);
		tabs = createTabs();
		logo = new VImage(script.getVikingWebsite() + logoUrl);
		containerImage = new VImage(script.getVikingWebsite() + CONTAINER_URL);
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		script.assertion(tabs != null, "ChatBox paint should not have null tabs List");
		
		logo.draw(g, LOGO_X, LOGO_Y);
		containerImage.draw(g, CONTAINER_X, CONTAINER_Y);	
		tabs.draw(g);
	}
	
	@Override
	public void reset()
	{
		tabs.reset();
	}
	
	protected abstract VTabBar createTabs();
}
