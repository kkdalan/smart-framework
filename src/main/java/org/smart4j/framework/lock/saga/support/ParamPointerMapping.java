package org.smart4j.framework.lock.saga.support;

import java.util.Map;

public interface ParamPointerMapping extends Map<ParamPointer, ParamPointer> {

	ParamPointer getMapping(ParamPointer source);

	void addMapping(ParamPointer source, ParamPointer target);

}
