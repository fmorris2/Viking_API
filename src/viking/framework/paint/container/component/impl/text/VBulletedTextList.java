package viking.framework.paint.container.component.impl.text;

import viking.framework.paint.container.VContainer;
import viking.framework.paint.container.component.impl.VComponentList;
import viking.framework.paint.image.VImage;

public class VBulletedTextList extends VComponentList<VBulletedTextComponent>
{
	private VImage defaultBulletImage;
	
	public VBulletedTextList(VContainer container, String url)
	{
		super(container);
		defaultBulletImage = new VImage(url);
	}
}
