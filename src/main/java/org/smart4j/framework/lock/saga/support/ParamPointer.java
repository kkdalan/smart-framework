package org.smart4j.framework.lock.saga.support;

public interface ParamPointer {

	String resolveValue();

	String injectValue(String value);
	
	Object getData();

}
