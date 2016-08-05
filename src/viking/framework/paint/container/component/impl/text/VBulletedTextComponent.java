package viking.framework.paint.container.component.impl.text;

import java.awt.Font;

import viking.framework.paint.container.UpdateableText;
import viking.framework.paint.container.VContainer;
import viking.framework.paint.image.VImage;

public class VBulletedTextComponent extends VTextComponent
{
	private VImage image;
	
	public VBulletedTextComponent(VContainer container, UpdateableText text, Font font)
	{
		super(container, text, font);
	}
	
	public VBulletedTextComponent(VContainer container, UpdateableText text, Font font, String imageUrl)
	{
		super(container, text, font);
		this.image = new VImage(imageUrl);
	}

}
