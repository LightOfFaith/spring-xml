package com.share.lifetime.support.zookeeper;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractZookeeperClient<TargetChildListener> implements ZookeeperClient {

	private final String connectString;

	private String username;

	private String password;

	private final Set<StateListener> stateListeners = new CopyOnWriteArraySet<StateListener>();

	private final ConcurrentMap<String, ConcurrentMap<ChildListener, TargetChildListener>> childListeners = new ConcurrentHashMap<String, ConcurrentMap<ChildListener, TargetChildListener>>();

	private volatile boolean closed = false;

	public AbstractZookeeperClient(String connectString) {
		this(connectString, null, null);
	}

	public AbstractZookeeperClient(String connectString, String username, String password) {
		this.connectString = connectString;
		this.username = username;
		this.password = password;
	}

	@Override
	public String getConnectString() {
		return connectString;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void create(String path, boolean ephemeral) {
		if (!ephemeral) {
			if (checkExists(path)) {
				return;
			}
		}
		int i = path.lastIndexOf('/');
		if (i > 0) {
			create(path.substring(0, i), false);
		}
		if (ephemeral) {
			createEphemeral(path);
		} else {
			createPersistent(path);
		}
	}

	@Override
	public void addStateListener(StateListener listener) {
		stateListeners.add(listener);
	}

	@Override
	public void removeStateListener(StateListener listener) {
		stateListeners.remove(listener);
	}

	public Set<StateListener> getSessionListeners() {
		return stateListeners;
	}

	@Override
	public List<String> addChildListener(String path, final ChildListener listener) {
		ConcurrentMap<ChildListener, TargetChildListener> listeners = childListeners.get(path);
		if (listeners == null) {
			childListeners.putIfAbsent(path, new ConcurrentHashMap<ChildListener, TargetChildListener>());
			listeners = childListeners.get(path);
		}
		TargetChildListener targetListener = listeners.get(listener);
		if (targetListener == null) {
			listeners.putIfAbsent(listener, createTargetChildListener(path, listener));
			targetListener = listeners.get(listener);
		}
		return addTargetChildListener(path, targetListener);
	}

	@Override
	public void removeChildListener(String path, ChildListener listener) {
		ConcurrentMap<ChildListener, TargetChildListener> listeners = childListeners.get(path);
		if (listeners != null) {
			TargetChildListener targetListener = listeners.remove(listener);
			if (targetListener != null) {
				removeTargetChildListener(path, targetListener);
			}
		}
	}

	protected void stateChanged(int state) {
		for (StateListener sessionListener : getSessionListeners()) {
			sessionListener.stateChanged(state);
		}
	}

	@Override
	public void close() {
		if (closed) {
			return;
		}
		closed = true;
		try {
			doClose();
		} catch (Throwable t) {
			log.warn(t.getMessage(), t);
		}
	}

	/**
	 * 
	 * @return scheme:auth
	 */
	public String getAuthority() {
		if ((username == null || username.length() == 0) && (password == null || password.length() == 0)) {
			return null;
		}
		return (username == null ? "" : username) + ":" + (password == null ? "" : password);
	}

	protected abstract void doClose();

	protected abstract void createPersistent(String path);

	protected abstract void createEphemeral(String path);

	protected abstract boolean checkExists(String path);

	protected abstract TargetChildListener createTargetChildListener(String path, ChildListener listener);

	protected abstract List<String> addTargetChildListener(String path, TargetChildListener listener);

	protected abstract void removeTargetChildListener(String path, TargetChildListener listener);

}
