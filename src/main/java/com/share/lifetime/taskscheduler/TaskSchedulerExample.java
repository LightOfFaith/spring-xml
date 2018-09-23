package com.share.lifetime.taskscheduler;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;

public class TaskSchedulerExample implements TaskScheduler {

	@Override
	public ScheduledFuture<?> schedule(Runnable task, Trigger trigger) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduledFuture<?> schedule(Runnable task, Date startTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Date startTime, long period) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
		// TODO Auto-generated method stub
		return null;
	}

}
