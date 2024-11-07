package org.smart4j.framework.util.lock.manager;

import org.smart4j.framework.util.lock.LockableTarget;

public interface LockManager {

	void addLockableTarget(LockableTarget target);

	void lockTarget(String id);

	void unlockTarget(String id);

}