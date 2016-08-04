package viking.framework.paint;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;

import viking.framework.script.VikingScript;


public class VikingFont
{
	public final Font NORMAL, BOLD, ITALIC, BOLD_ITALIC;
	
	private final String BASE_URL;
	private Font current;
	
	public VikingFont(VikingScript script)
	{
		BASE_URL = script.getVikingWebsite() + "/script/font/";
		NORMAL = downloadFont("TrebuchetMS.ttf");
		BOLD = downloadFont("TrebuchetMSBold.ttf");
		ITALIC = downloadFont("TrebuchetMSItalic.ttf");
		BOLD_ITALIC = downloadFont("TrebuchetMSBoldItalic.ttf");
		current = NORMAL;
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
		
		return Font.getFont("Verdana");
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
