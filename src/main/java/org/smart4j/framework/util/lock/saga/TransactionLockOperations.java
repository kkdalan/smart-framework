package org.smart4j.framework.util.lock.saga;

public interface TransactionLockOperations {

	void lockTransaction(String sagaId);

	void unlockTransaction();

}