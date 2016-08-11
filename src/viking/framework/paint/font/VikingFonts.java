package viking.framework.paint.font;

import java.awt.Font;

import viking.framework.script.VikingScript;


public class VikingFonts
{
	public final VFont MEDIUM, MEDIUM_ITALIC, BOLD, BOLD_ITALIC,
		LIGHT, LIGHT_ITALIC;
	
	private VFont current;
	
	public VikingFonts(VikingScript script)
	{
		MEDIUM = new VFont(script, "Roboto-Medium.ttf");
		MEDIUM_ITALIC = new VFont(script, "Roboto-MediumItalic.ttf");
		BOLD = new VFont(script, "Roboto-Bold.ttf");
		BOLD_ITALIC = new VFont(script, "Roboto-BoldItalic.ttf");
		LIGHT = new VFont(script, "Roboto-Light.ttf");
		LIGHT_ITALIC = new VFont(script, "Roboto-LightItalic.ttf");
		current = MEDIUM;
	}
	
	public void switchFont(VFont f)
	{
		current = f;
	}
	
	public Font getCurrent(int size)
	{
		return current.get(size);
	}
}
