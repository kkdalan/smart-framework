package org.smart4j.framework.util.lock.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.smart4j.framework.util.lock.LockableTarget;
import org.smart4j.framework.util.lock.manager.LockManager;
import org.smart4j.framework.util.lock.saga.impl.SagaLock;

public class SagaLockManager implements LockManager {

	private List<SagaLock> sagaLockTargets;

	public SagaLockManager() {
		this.sagaLockTargets = new ArrayList<>();
	}

	@Override
	public void addLockableTarget(LockableTarget target) {
		sagaLockTargets.add((SagaLock)target);
	}

	@Override
	public void lockTarget(String sagaId) {
		SagaLock target = findLockableTarget(sagaId);
		if (target != null) {
			target.setLocked(true);
		}
	}

	@Override
	public void unlockTarget(String sagaId) {
		SagaLock target = findLockableTarget(sagaId);
		if (target != null) {
			target.setLocked(false);
		}
	}

	private SagaLock findLockableTarget(String sagaId) {
		for (SagaLock target : sagaLockTargets) {
			if (target.getSagaId().equals(sagaId)) {
				return target;
			}
		}
		return null;
	}
}