package viking.framework.averager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class MovingAverager
{
	private static final int TIME_WINDOW = 60000 * 30; //30 minute time window
	private static final int TIME_BETWEEN_SAMPLES = 10000; //time between each data sample
	private static final int TOTAL_SAMPLES = TIME_WINDOW / TIME_BETWEEN_SAMPLES;
	
	private Map<String, Queue<Double>> data;
	private Map<String, Double> dataTotals;
	private DecimalFormat format;
	
	public MovingAverager(DecimalFormat format)
	{
		this.format = format;
		data = new HashMap<>();
		dataTotals = new HashMap<>();
	}
	
	public void add(String key, double value)
	{
		Queue<Double> queue = data.get(key);
		Double total = dataTotals.get(key);
		
		if(queue == null)
			data.put(key, new ArrayBlockingQueue<>(TOTAL_SAMPLES));
		else if(queue.size() == TOTAL_SAMPLES)
			dataTotals.put(key, total - queue.poll());
		
		queue.add(value);
		dataTotals.put(key, total == null ? value : total + value);
	}
	
	public String getAvg(String key)
	{
		Queue<Double> queue = data.get(key);
		Double total = dataTotals.get(key);	
		return total == null ? "0.0" : format.format(total / queue.size());
	}
	
}
