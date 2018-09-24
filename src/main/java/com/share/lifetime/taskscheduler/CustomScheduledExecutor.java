package com.share.lifetime.taskscheduler;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class CustomScheduledExecutor extends ScheduledThreadPoolExecutor {

	public CustomScheduledExecutor(int corePoolSize) {
		super(corePoolSize);
	}

}
