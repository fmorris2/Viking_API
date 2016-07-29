package viking.framework.averager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import viking.api.Timing;

public class MovingAverager
{
	private static final int TIME_WINDOW = 60000 * 30; //30 minute time window
	private static final int TIME_BETWEEN_SAMPLES = 1000; //time between each data sample
	private static final int TOTAL_SAMPLES = TIME_WINDOW / TIME_BETWEEN_SAMPLES;
	
	private Map<String, Queue<Double>> data;
	private Map<String, Double> dataTotals;
	private DecimalFormat format;
	private long lastSample;
	
	public MovingAverager(DecimalFormat format)
	{
		this.format = format;
		data = new HashMap<>();
		dataTotals = new HashMap<>();
	}
	
	public void add(String key, double value)
	{
		if(Timing.timeFromMark(lastSample) < TIME_BETWEEN_SAMPLES)
			return;
		
		Queue<Double> queue = data.get(key);
		Double total = dataTotals.get(key);
		
		if(queue == null)
			data.put(key, new ArrayBlockingQueue<>(TOTAL_SAMPLES));
		else if(queue.size() == TOTAL_SAMPLES)
		{
			total -= queue.poll();
			dataTotals.put(key, total);
		}
		
		queue.add(value);
		dataTotals.put(key, total == null ? value : total + value);
		lastSample = Timing.currentMs();
	}
	
	public String getAvg(String key)
	{
		Queue<Double> queue = data.get(key);
		Double total = dataTotals.get(key);	
		return total == null ? "0.0" : format.format(total / queue.size());
	}
	
}
