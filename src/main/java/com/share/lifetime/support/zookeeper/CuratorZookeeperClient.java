package com.share.lifetime.support.zookeeper;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import org.apache.zookeeper.WatchedEvent;

public class CuratorZookeeperClient extends AbstractZookeeperClient<CuratorWatcher> {

	private final CuratorFramework client;

	/**
	 * 
	 * @param connectString
	 */
	public CuratorZookeeperClient(String connectString) {
		this(connectString, null, null);
	}

	/**
	 * 
	 * @param connectString
	 * @param username      scheme
	 * @param password      auth
	 * 
	 *                      username:password = scheme:auth
	 */
	public CuratorZookeeperClient(String connectString, String username, String password) {
		super(connectString, username, password);
		try {
			CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString(connectString)
					.retryPolicy(new RetryNTimes(1, 1000)).connectionTimeoutMs(5000);
			String authority = getAuthority();
			if (authority != null && authority.length() > 0) {
				builder = builder.authorization("digest", authority.getBytes());
			}
			client = builder.build();
			client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
				@Override
				public void stateChanged(CuratorFramework client, ConnectionState state) {
					if (state == ConnectionState.LOST) {
						CuratorZookeeperClient.this.stateChanged(StateListener.DISCONNECTED);
					} else if (state == ConnectionState.CONNECTED) {
						CuratorZookeeperClient.this.stateChanged(StateListener.CONNECTED);
					} else if (state == ConnectionState.RECONNECTED) {
						CuratorZookeeperClient.this.stateChanged(StateListener.RECONNECTED);
					}
				}
			});
			client.start();
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	@Override
	public void createPersistent(String path) {
		try {
			client.create().forPath(path);
		} catch (NodeExistsException e) {
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	@Override
	public void createEphemeral(String path) {
		try {
			client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
		} catch (NodeExistsException e) {
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(String path) {
		try {
			client.delete().forPath(path);
		} catch (NoNodeException e) {
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	@Override
	public List<String> getChildren(String path) {
		try {
			return client.getChildren().forPath(path);
		} catch (NoNodeException e) {
			return null;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	@Override
	public boolean checkExists(String path) {
		try {
			if (client.checkExists().forPath(path) != null) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean isConnected() {
		return client.getZookeeperClient().isConnected();
	}

	@Override
	public void doClose() {
		client.close();
	}

	@Override
	public CuratorWatcher createTargetChildListener(String path, ChildListener listener) {
		return new CuratorWatcherImpl(listener);
	}

	@Override
	public List<String> addTargetChildListener(String path, CuratorWatcher listener) {
		try {
			return client.getChildren().usingWatcher(listener).forPath(path);
		} catch (NoNodeException e) {
			return null;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	@Override
	public void removeTargetChildListener(String path, CuratorWatcher listener) {
		((CuratorWatcherImpl) listener).unwatch();
	}

	private class CuratorWatcherImpl implements CuratorWatcher {

		private volatile ChildListener listener;

		public CuratorWatcherImpl(ChildListener listener) {
			this.listener = listener;
		}

		public void unwatch() {
			this.listener = null;
		}

		@Override
		public void process(WatchedEvent event) throws Exception {
			if (listener != null) {
				String path = event.getPath() == null ? "" : event.getPath();
				listener.childChanged(path,
						// if path is null, curator using watcher will throw NullPointerException.
						// if client connect or disconnect to server, zookeeper will queue
						// watched event(Watcher.Event.EventType.None, .., path = null).
						StringUtils.isNotEmpty(path) ? client.getChildren().usingWatcher(this).forPath(path)
								: Collections.<String>emptyList());
			}
		}
	}
}
