package viking.framework.paint.container.component.impl.text;

import java.awt.Font;
import java.awt.Graphics2D;

import viking.framework.paint.container.UpdateableText;
import viking.framework.paint.container.component.VComponent;

public class VTextComponent extends VComponent
{
	protected Font font;
	protected UpdateableText text;
	
	public VTextComponent(UpdateableText text, Font font)
	{
		this.text = text;
		this.font = font;
	}
	
	@Override
	public void draw(Graphics2D g)
	{
	}

	@Override
	public void reset()
	{
	}

}
