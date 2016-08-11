package viking.framework.paint.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import viking.framework.script.VikingScript;

public class VFont
{	
	private static final int DEFAULT_SIZE = 11;
	
	private final String BASE_URL;
	
	private Map<Integer, Font> sizes;
	private Font defaultFont;
	
	public VFont(VikingScript script, String url)
	{
		sizes = new HashMap<>();
		BASE_URL = script.getVikingWebsite() + "/script/font/";
		defaultFont = downloadFont(url);
	}
	
	public Font get(int size)
	{
		Font f = sizes.get(size);
		
		if(f == null)
		{
			f = defaultFont.deriveFont(Font.PLAIN, size);
			sizes.put(size, f);
		}
		
		return f;
	}
	
	private Font downloadFont(String url)
	{
		try
		{
			URL fontUrl = new URL(BASE_URL + url);
			return Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()).deriveFont(Font.PLAIN, DEFAULT_SIZE);
		}
		catch(FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
		
		return new Font("Verdana", Font.PLAIN, DEFAULT_SIZE);
	}
}
