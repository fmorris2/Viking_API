package viking.framework.paint.container.component.impl.text;

import java.awt.Font;

import viking.framework.paint.container.UpdateableText;
import viking.framework.paint.image.VImage;

public class VBulletedTextComponent extends VTextComponent
{
	private VImage image;
	
	public VBulletedTextComponent(UpdateableText text, Font font)
	{
		super(text, font);
	}
	
	public VBulletedTextComponent(UpdateableText text, Font font, String imageUrl)
	{
		super(text, font);
		this.image = new VImage(imageUrl);
	}

}
