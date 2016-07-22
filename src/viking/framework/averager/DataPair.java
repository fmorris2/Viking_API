package viking.framework.averager;

public class DataPair
{
	private double numTimes;
	private double totalValue;
	
	public DataPair(double value)
	{
		numTimes = 1;
		totalValue = value;
	}
	
	public double calcAvg()
	{
		return totalValue / numTimes;
	}
	
	public void add(double value)
	{
		numTimes++;
		totalValue += value;
	}
	
	public void reset()
	{
		numTimes = 0;
		totalValue = 0;
	}
}
