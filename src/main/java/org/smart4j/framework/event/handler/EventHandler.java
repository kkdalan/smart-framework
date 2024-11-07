package org.smart4j.framework.event.handler;

import org.smart4j.framework.event.model.Event;

public interface EventHandler {
	
	void publishEvent(Event event) throws Exception;

	void consumeEvent(Event event) throws Exception;
	
}
