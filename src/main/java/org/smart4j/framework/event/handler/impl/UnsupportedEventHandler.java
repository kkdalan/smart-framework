package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;

public enum UnsupportedEventHandler implements EventHandler {

	INSTANCE;

	@Override
	public void publishEvent(Event event) {
		throw new RuntimeException("Event handling not supported for event type: " + event.getEventType() + "(ID"
				+ event.getEventId() + ")");
	}

	@Override
	public void consumeEvent(Event event) {
		throw new RuntimeException("Event handling not supported for event type: " + event.getEventType() + "(ID"
				+ event.getEventId() + ")");
	}
}
