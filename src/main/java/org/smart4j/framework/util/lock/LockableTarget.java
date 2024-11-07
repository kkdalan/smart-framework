package org.smart4j.framework.util.lock;

public interface LockableTarget {
	
	void registerListener(LockStateListener listener);

	void removeListener(LockStateListener listener);

	void notifyListeners();
}