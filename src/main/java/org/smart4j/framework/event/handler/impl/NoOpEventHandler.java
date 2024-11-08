package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;

public enum NoOpEventHandler implements EventHandler {

	INSTANCE;

	@Override
	public void publishEvent(Event event) {
		System.err.println("No handler found for event type: " + event.getEventType());
	}

	@Override
	public void consumeEvent(Event event) {
		System.err.println("No handler found for event type: " + event.getEventType());
	}
}
