package viking.framework.averager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import viking.api.ScriptUtil;
import viking.framework.script.VikingScript;

public class Averager extends ScriptUtil
{
	private Map<String, DataPair> data;
	private DecimalFormat format;
	
	public Averager(VikingScript script)
	{
		super(script);
		data = new HashMap<>();
		format = new DecimalFormat("###.##");
	}
	
	public void add(String key, double value)
	{
		DataPair dp = data.get(key);
		
		if(dp == null)
			data.put(key, new DataPair(value));
		else
			dp.add(value);
	}
	
	public String getAvg(String key)
	{
		DataPair dp = data.get(key);
		
		if(dp == null)
			return "0.0";
		else
			return format.format(dp.calcAvg());
			
	}

}
