package org.smart4j.framework.lock.saga.impl;

import java.util.ArrayList;
import java.util.List;

import org.smart4j.framework.lock.LockStateListener;
import org.smart4j.framework.lock.LockableTarget;
import org.smart4j.framework.lock.saga.SagaLockOperations;

public class SagaLock implements LockableTarget, SagaLockOperations {

	// 交易代號
	private String sagaId;
	// 語意鎖
	private SmtLock smtLock;
	// 樂觀鎖
	private Integer optLock; // 什麼時候使用？

	private List<LockStateListener> listeners;

	public SagaLock() {
	}

	public SagaLock(String sagaId) {
		this(sagaId, SmtLock.NORMAL, 0);
	}

	public SagaLock(String sagaId, SmtLock smtLock, Integer optLock) {
		this.sagaId = sagaId;
		this.smtLock = smtLock;
		this.optLock = optLock;
		this.listeners = new ArrayList<>();
	}

	@Override
	public void registerListener(LockStateListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(LockStateListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void notifyListeners() {
		for (LockStateListener listener : listeners) {
			listener.lockStateUpdated(getSagaId(), isLocked());
		}
	}

	public void setLocked(boolean isLocked) {
		this.smtLock = SmtLock.LOCK;
		notifyListeners();
	}

	public boolean isLocked() {
		return SmtLock.LOCK.equals(this.smtLock);
	}

	public String getSagaId() {
		return this.sagaId;
	}

	/* -------- 鎖定相關操作方法 -------- */

	@Override
	public void lockTransaction(String sagaId) {
		this.smtLock = SmtLock.LOCK;
		this.sagaId = sagaId;
		notifyListeners();
	}

	@Override
	public void unlockTransaction() {
		this.smtLock = SmtLock.NORMAL;
		this.sagaId = null;
		notifyListeners();
	}

	@Override
	public void lockCompensation() {
		this.smtLock = SmtLock.NORMAL;
		this.sagaId = null;
		notifyListeners();
	}

	@Override
	public void unlockCompensation(SmtLock smtLock, String sagaId) {
		this.smtLock = smtLock;
		this.sagaId = sagaId;
		notifyListeners();
	}

}