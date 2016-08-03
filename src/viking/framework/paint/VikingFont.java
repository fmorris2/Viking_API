package viking.framework.paint;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;

import viking.framework.script.VikingScript;


public class VikingFont
{
	private final String BASE_URL;
	public final Font NORMAL, BOLD, ITALIC, BOLD_ITALIC;
	
	public VikingFont(VikingScript script)
	{
		BASE_URL = script.getVikingWebsite() + "/script/font/";
		NORMAL = downloadFont("TrebuchetMS.ttf");
		BOLD = downloadFont("TrebuchetMSBold.ttf");
		ITALIC = downloadFont("TrebuchetMSItalic.ttf");
		BOLD_ITALIC = downloadFont("TrebuchetMSBoldItalic.ttf");
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
}
