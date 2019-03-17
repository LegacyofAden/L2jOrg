package org.l2j.gameserver;

import org.l2j.commons.threading.PriorityThreadFactory;
import org.l2j.commons.threading.RejectedExecutionHandlerImpl;

import java.util.concurrent.*;

public class ThreadPoolManager
{
	private static final long MAX_DELAY = TimeUnit.NANOSECONDS.toMillis(Long.MAX_VALUE - System.nanoTime()) / 2;

	private static final ThreadPoolManager _instance = new ThreadPoolManager();

	public static ThreadPoolManager getInstance() {
		return _instance;
	}

	private final ScheduledThreadPoolExecutor _scheduledExecutor;
	private final ThreadPoolExecutor _executor;

	private boolean _shutdown;

	private ThreadPoolManager() {
		RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandlerImpl();

		var processors = Runtime.getRuntime().availableProcessors();
		_scheduledExecutor = new ScheduledThreadPoolExecutor(processors *4, new PriorityThreadFactory("ScheduledThreadPool", Thread.NORM_PRIORITY), new ThreadPoolExecutor.CallerRunsPolicy());
		_scheduledExecutor.setRejectedExecutionHandler(rejectedHandler);

		_executor = new ThreadPoolExecutor(processors * 6, Integer.MAX_VALUE, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new PriorityThreadFactory("ThreadPoolExecutor", Thread.NORM_PRIORITY), new ThreadPoolExecutor.CallerRunsPolicy());
		_executor.setRejectedExecutionHandler(rejectedHandler);

		scheduleAtFixedRate(() -> { _scheduledExecutor.purge(); _executor.purge();  }, 300000L, 300000L);
	}

	private long validate(long delay)
	{
        long validatedDelay = Math.max(0, Math.min(ThreadPoolManager.MAX_DELAY, delay));
        if (delay > validatedDelay)
            return -1;

        long secondsToRestart = Shutdown.getInstance().getSecondsToRestart() * 1000L;
        if(secondsToRestart > 0 && validatedDelay > secondsToRestart)
            return -1;

        return validatedDelay;
	}

	public boolean isShutdown()
	{
		return _shutdown;
	}

	public ScheduledFuture<?> schedule(Runnable r, long delay)
	{
        delay = validate(delay);
        if(delay == -1)
            return null;

        return _scheduledExecutor.schedule(r, delay, TimeUnit.MILLISECONDS);
	}

	public ScheduledFuture<?> scheduleAtFixedRate(Runnable r, long initial, long delay)
	{
        initial = validate(initial);
        if(initial == -1)
            return null;

        delay = validate(delay);
        if(delay == -1)
            return _scheduledExecutor.schedule(r, initial, TimeUnit.MILLISECONDS);

        return _scheduledExecutor.scheduleAtFixedRate(r, initial, delay, TimeUnit.MILLISECONDS);
	}

	public ScheduledFuture<?> scheduleAtFixedDelay(Runnable r, long initial, long delay)
	{
        initial = validate(initial);
        if(initial == -1)
            return null;

        delay = validate(delay);
        if(delay == -1)
            return _scheduledExecutor.schedule(r, initial, TimeUnit.MILLISECONDS);

        return _scheduledExecutor.scheduleWithFixedDelay(r, initial, delay, TimeUnit.MILLISECONDS);
	}

	public static void execute(Runnable r) {
		getInstance()._executor.execute(r);
	}

	public void shutdown() throws InterruptedException
	{
		_shutdown = true;
		try
		{
			_scheduledExecutor.shutdown();
			_scheduledExecutor.awaitTermination(10, TimeUnit.SECONDS);
		}
		finally
		{
			_executor.shutdown();
			_executor.awaitTermination(1, TimeUnit.MINUTES);
		}
	}

	public CharSequence getStats()
	{
		StringBuilder list = new StringBuilder();

		list.append("ScheduledThreadPool\n");
		treadPoolStats(list, _scheduledExecutor);
		list.append("ThreadPoolExecutor\n");
		treadPoolStats(list, _executor);

		return list;
	}

	private void treadPoolStats(StringBuilder list, ThreadPoolExecutor scheduledExecutor) {
		list.append("=================================================\n");
		list.append("\tgetActiveCount: ...... ").append(scheduledExecutor.getActiveCount()).append("\n");
		list.append("\tgetCorePoolSize: ..... ").append(scheduledExecutor.getCorePoolSize()).append("\n");
		list.append("\tgetPoolSize: ......... ").append(scheduledExecutor.getPoolSize()).append("\n");
		list.append("\tgetLargestPoolSize: .. ").append(scheduledExecutor.getLargestPoolSize()).append("\n");
		list.append("\tgetMaximumPoolSize: .. ").append(scheduledExecutor.getMaximumPoolSize()).append("\n");
		list.append("\tgetCompletedTaskCount: ").append(scheduledExecutor.getCompletedTaskCount()).append("\n");
		list.append("\tgetQueuedTaskCount: .. ").append(scheduledExecutor.getQueue().size()).append("\n");
		list.append("\tgetTaskCount: ........ ").append(scheduledExecutor.getTaskCount()).append("\n");
	}
}