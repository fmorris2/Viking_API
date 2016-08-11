package viking.framework.paint;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;

import viking.framework.script.VikingScript;


public class VikingFont
{
	public final Font MEDIUM, MEDIUM_ITALIC, BOLD, BOLD_ITALIC,
		LIGHT, LIGHT_ITALIC;
	
	private final String BASE_URL;
	private Font current;
	
	public VikingFont(VikingScript script)
	{
		BASE_URL = script.getVikingWebsite() + "/script/font/";
		MEDIUM = downloadFont("Roboto-Medium.ttf");
		MEDIUM_ITALIC = downloadFont("Roboto-MediumItalic.ttf");
		BOLD = downloadFont("Roboto-Bold.ttf");
		BOLD_ITALIC = downloadFont("Roboto-BoldItalic.ttf");
		LIGHT = downloadFont("Roboto-Light.ttf");
		LIGHT_ITALIC = downloadFont("Roboto-LightItalic.ttf");
		current = MEDIUM;
	}
	
	private Font downloadFont(String url)
	{
		try
		{
			URL fontUrl = new URL(BASE_URL + url);
			return Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()).deriveFont(Font.PLAIN, 11);
		}
		catch(FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
		
		return new Font("Verdana", Font.PLAIN, 11);
	}
	
	public void switchFont(Font f)
	{
		current = f;
	}
	
	public Font getCurrent()
	{
		return current;
	}
}
