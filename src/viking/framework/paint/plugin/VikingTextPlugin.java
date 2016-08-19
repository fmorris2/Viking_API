package viking.framework.paint.plugin;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import viking.framework.paint.VikingPaint;
import viking.framework.script.VikingScript;

public abstract class VikingTextPlugin extends VikingPaintPlugin
{
	protected Font font;
	
	public VikingTextPlugin(VikingScript script, VikingPaint<?> paint, Font font)
	{
		super(script, paint);
		this.font = font;
	}
	
	@Override
	public void handle(Graphics2D g)
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);
		g.setFont(font);
		super.handle(g);
	}
}
