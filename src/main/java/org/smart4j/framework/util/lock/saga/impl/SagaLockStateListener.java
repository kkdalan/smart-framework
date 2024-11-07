package org.smart4j.framework.util.lock.saga.impl;

import org.smart4j.framework.util.lock.LockStateListener;

public class SagaLockStateListener implements LockStateListener {
	
	private String name;

	public SagaLockStateListener(String name) {
		this.name = name;
	}

	@Override
	public void lockStateUpdated(String sagaId, boolean isLocked) {
		System.out.println("Listener " + name + " received update: Target " + sagaId + " is "
				+ (isLocked ? "locked" : "unlocked"));
	}
}