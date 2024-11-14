package org.smart4j.framework.lock;

public interface LockStateListener {

	void lockStateUpdated(String id, boolean isLocked);

}
