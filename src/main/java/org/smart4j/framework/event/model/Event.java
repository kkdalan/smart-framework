package org.smart4j.framework.event.model;

public interface Event {

	String getEventId();

	String getEventData();
	
	EventType getEventType();
	

}