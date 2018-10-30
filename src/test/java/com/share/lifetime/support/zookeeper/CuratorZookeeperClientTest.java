package com.share.lifetime.support.zookeeper;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CuratorZookeeperClientTest {

	CuratorZookeeperClient client = null;

	String connectString = "127.0.0.1:2181";

	@Before
	public void before() {

		client = new CuratorZookeeperClient(connectString);

	}

	@Test
	public void testConnection() {
		boolean checkExists = client.checkExists("/dubbo");
		log.info("testConnection:{}", checkExists);
	}

	@Test
	public void testDoClose() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreatePersistent() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateEphemeral() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckExists() {
		fail("Not yet implemented");
	}

	@Test
	public void testCuratorZookeeperClientString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCuratorZookeeperClientStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChildren() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsConnected() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateTargetChildListenerStringChildListener() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddTargetChildListenerStringCuratorWatcher() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveTargetChildListenerStringCuratorWatcher() {
		fail("Not yet implemented");
	}

}
