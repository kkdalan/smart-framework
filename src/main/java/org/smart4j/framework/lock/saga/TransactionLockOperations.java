package org.smart4j.framework.lock.saga;

public interface TransactionLockOperations {

	void lockTransaction(String sagaId);

	void unlockTransaction();

}