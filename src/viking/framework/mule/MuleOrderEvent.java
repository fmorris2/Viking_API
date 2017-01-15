package viking.framework.mule;

import org.osbot.rs07.api.Bank.BankMode;
import org.osbot.rs07.api.map.Position;

import viking.api.Timing;
import viking.framework.script.VikingScript;

public class MuleOrderEvent
{
	private static final int MULE_DIST_THRESH = 10;
	
	private VikingScript script;
	private MuleOrder order;
	private Position mulePos;
	private String muleName;
	private boolean hasFinished, hasWalkedToMule, hasWithdrawnOrder;
	
	public MuleOrderEvent(VikingScript s, MuleOrder o)
	{
		script = s;
		order = o;
	}
	
	public void execute()
	{
		script.log(this, false, "MuleOrderEvent execute");
		
		if(muleName != null && mulePos != null)	
		{
			script.log(this, false, "Has mule info...");
			if(!hasWalkedToMule && walkToMule())
				hasWalkedToMule = true;
			
			if(hasWalkedToMule)
			{
				if(!hasWithdrawnOrder)
					withdrawOrder();
				else if(walkToMule())
					tradeMule();
			}
			
			
				
		}
		else
			script.log(this, false, "Mule info has not been provided yet...");
	}
	
	private void tradeMule()
	{
		script.log(this, false , "trade mule");
	}
	
	private boolean walkToMule()
	{
		if(script.myPosition().distance(mulePos) <= MULE_DIST_THRESH)
			return true;
		
		return script.getUtils().walk.walkTo(mulePos)
					&& Timing.waitCondition(() -> script.myPosition().distance(mulePos) <= MULE_DIST_THRESH, 3500);
	}
	
	private void withdrawOrder()
	{
		if(!script.getUtils().bank.isInBank())
			script.getWalking().webWalk(script.getUtils().bank.getAllBanks(false, false));
		else if(!script.getBank().isOpen())
			script.getUtils().bank.open();
		else if(script.bank.enableMode(BankMode.WITHDRAW_NOTE))
		{
			for(int i : order.ITEMS)
				if(script.bank.contains(i) && !script.bank.withdrawAll(i))
					break;
			
			hasWithdrawnOrder = true;
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
