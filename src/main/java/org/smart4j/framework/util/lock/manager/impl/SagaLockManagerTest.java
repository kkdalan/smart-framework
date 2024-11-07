package org.smart4j.framework.util.lock.manager.impl;

import org.smart4j.framework.util.lock.manager.LockManager;
import org.smart4j.framework.util.lock.saga.impl.SagaLock;
import org.smart4j.framework.util.lock.saga.impl.SagaLockStateListener;

// 測試代碼
public class SagaLockManagerTest {

	public static void main(String[] args) {

		LockManager lockManager = new SagaLockManager();

		// 建立兩個鎖定的物件
		SagaLock obj1 = new SagaLock("Entity1");
		SagaLock obj2 = new SagaLock("Entity2");

		lockManager.addLockableTarget(obj1);
		lockManager.addLockableTarget(obj2);

		// 建立兩個觀察者來監聽物件狀態
		SagaLockStateListener listener1 = new SagaLockStateListener("Listener1");
		SagaLockStateListener listener2 = new SagaLockStateListener("Listener2");

		// 註冊觀察者
		obj1.registerListener(listener1);
		obj2.registerListener(listener2);

		// 鎖定和解鎖物件，觀察者會收到更新
		lockManager.lockTarget("Entity1");
		lockManager.lockTarget("Entity2");
		lockManager.unlockTarget("Entity1");
	}
}
