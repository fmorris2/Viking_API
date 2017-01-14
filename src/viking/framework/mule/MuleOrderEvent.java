package viking.framework.mule;

import org.osbot.rs07.api.map.Position;

import viking.framework.script.VikingScript;

public class MuleOrderEvent
{
	private VikingScript script;
	private MuleOrder order;
	private Position mulePos;
	private String muleName;
	private boolean hasFinished;
	
	public MuleOrderEvent(VikingScript s, MuleOrder o)
	{
		script = s;
		order = o;
	}
	
	public void execute()
	{
		while(!hasFinished)
		{
			script.log(this, false, "MuleOrderEvent execute");
			
			if(muleName != null && mulePos != null)	
			{
				script.log(this, false, "Has mule info...");
			}
			else
				script.log(this, false, "Mule info has not been provided yet...");
			
			script.getUtils().bank.waitMs(400);
		}
	}
	
	public boolean shouldExecute()
	{
		return order.isReady();
	}
	
	public boolean hasFinished()
	{
		return hasFinished;
	}
	
	public void setMuleName(String n)
	{
		muleName = n;
	}
	
	public void setMulePos(Position p)
	{
		mulePos = p;
	}
	
	public MuleOrder getOrder()
	{
		return order;
	}
}
