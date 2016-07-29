package viking.framework.averager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import viking.api.ScriptUtil;
import viking.framework.script.VikingScript;

public class Averager extends ScriptUtil
{
	private Map<String, DataPair> data;
	private MovingAverager movingAverager;
	private DecimalFormat format;
	
	public Averager(VikingScript script)
	{
		super(script);
		data = new HashMap<>();
		format = new DecimalFormat("###.##");
		movingAverager = new MovingAverager(format);
	}
	
	public void add(String key, double value)
	{
		movingAverager.add(key, value);
		
		DataPair dp = data.get(key);
		
		if(dp == null)
			data.put(key, new DataPair(value));
		else
			dp.add(value);
	}
	
	public String getAvg(String key)
	{
		DataPair dp = data.get(key);
		return dp == null ? "0.0" : format.format(dp.calcAvg());		
	}
	
	public String getMoving(String key)
	{
		return movingAverager.getAvg(key);
	}
}
