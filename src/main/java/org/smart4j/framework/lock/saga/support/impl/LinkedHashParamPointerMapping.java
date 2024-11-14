package org.smart4j.framework.lock.saga.support.impl;

import java.util.LinkedHashMap;

import org.smart4j.framework.lock.saga.support.ParamPointer;
import org.smart4j.framework.lock.saga.support.ParamPointerMapping;

public class LinkedHashParamPointerMapping extends LinkedHashMap<ParamPointer, ParamPointer>
		implements ParamPointerMapping {

	@Override
	public void addMapping(ParamPointer source, ParamPointer target) {
		put(source, target);
	}

	@Override
	public ParamPointer getMapping(ParamPointer source) {
		return get(source);
	}

}
