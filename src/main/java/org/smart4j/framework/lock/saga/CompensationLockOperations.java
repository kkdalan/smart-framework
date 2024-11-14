package org.smart4j.framework.lock.saga;

import org.smart4j.framework.lock.saga.impl.SmtLock;

public interface CompensationLockOperations {

	void lockCompensation();

	void unlockCompensation(SmtLock smtLock, String sagaId);

}