package org.smart4j.framework.util.saga.support;

import java.util.Map;

public interface ParamPointerMapping extends Map<ParamPointer, ParamPointer> {

	ParamPointer getMapping(ParamPointer source);

	void addMapping(ParamPointer source, ParamPointer target);

}
