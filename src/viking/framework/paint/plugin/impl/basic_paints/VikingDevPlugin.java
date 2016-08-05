package viking.framework.paint.plugin.impl.basic_paints;

import java.awt.Color;
import java.lang.management.ManagementFactory;

import viking.api.Timing;
import viking.framework.averager.Averager;
import viking.framework.paint.VikingPaint;
import viking.framework.paint.plugin.impl.BasicVikingPlugin;
import viking.framework.script.VikingScript;

import com.sun.management.OperatingSystemMXBean;

public class VikingDevPlugin extends BasicVikingPlugin
{
	private static final int PAINT_X = 4;
	private static final int PAINT_BOT_Y = 336;
	
	private OperatingSystemMXBean bean;
	private Averager avg;
	
	public VikingDevPlugin(VikingScript script, VikingPaint<?> p)
	{
		super(script, p, Color.WHITE, 0.8F, PAINT_X, PAINT_BOT_Y);
		bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		avg = script.getUtils().getAverager();
		isVisible = script.isDevBuild();
	}

	@Override
	protected String[] getInfo()
	{
		avg.add("cpu", bean.getProcessCpuLoad() * 100);
		
		return new String[]
		{
			"Time ran: " + Timing.msToString(paint.getTimeRan()),
			"Moving CPU usage: " + avg.getMoving("cpu") + "%",
			"Available cores: " + bean.getAvailableProcessors(),
			"RAM usage: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000 
				+ "/" + bean.getCommittedVirtualMemorySize() / 1000000 + "Mb",
			"Physical memory usage: " + bean.getFreePhysicalMemorySize() / 1000000
				+ "/" + bean.getTotalPhysicalMemorySize() / 1000000 + "Mb",
		};
	}

	@Override
	public void reset()
	{
		avg.reset();
	}
}
