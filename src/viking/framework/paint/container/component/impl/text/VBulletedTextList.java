package viking.framework.paint.container.component.impl.text;

import viking.framework.paint.container.component.impl.VComponentList;
import viking.framework.paint.image.VImage;

public class VBulletedTextList extends VComponentList<VBulletedTextComponent>
{
	private VImage defaultBulletImage;
	
	public VBulletedTextList(String url)
	{
		defaultBulletImage = new VImage(url);
	}
}
