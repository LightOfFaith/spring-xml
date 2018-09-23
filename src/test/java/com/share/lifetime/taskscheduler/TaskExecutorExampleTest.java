package com.share.lifetime.taskscheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring-taskscheduler.xml" })
public class TaskExecutorExampleTest {

	@Autowired
	private TaskExecutorExample taskExecutorExample;

	@Test
	public void testPrintMessages() {
		taskExecutorExample.printMessages();
	}

}
