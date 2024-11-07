package org.smart4j.framework.util.lock.saga.impl;

public enum SmtLock {

	LOCK("Y"), // 鎖定中
	NORMAL("N"); // 一般狀態

	private final String value;

	private SmtLock(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}