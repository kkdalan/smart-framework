package org.smart4j.framework.util.lock;

public interface LockStateListener {

	void lockStateUpdated(String id, boolean isLocked);

}
