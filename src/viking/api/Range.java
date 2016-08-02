package viking.api;

public class Range
{
	private double lower;
	private double upper;
	private double diff;
	
	public Range(double lower, double upper)
	{
		this.lower = lower;
		this.upper = upper;
		diff = upper - lower;
	}
	
	public double calculateByPercent(double percent)
	{
		return lower + (diff * percent);
	}
	
	public double getUpper()
	{
		return upper;
	}
	
	public double getLower()
	{
		return lower;
	}
}
