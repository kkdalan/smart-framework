package org.smart4j.framework.util.lock.saga;

import org.smart4j.framework.util.lock.saga.impl.SmtLock;

public interface CompensationLockOperations {

	void lockCompensation();

	void unlockCompensation(SmtLock smtLock, String sagaId);

}