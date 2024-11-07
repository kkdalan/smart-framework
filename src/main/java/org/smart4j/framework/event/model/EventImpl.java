package org.smart4j.framework.event.model;

public class EventImpl implements Event {

	private final String eventId;
	private final String eventData;
	private final EventType eventType;

	public EventImpl(String eventId, String eventData, EventType eventType) {
		this.eventId = eventId;
		this.eventData = eventData;
		this.eventType = eventType;
	}

	@Override
	public String getEventId() {
		return eventId;
	}

	@Override
	public String getEventData() {
		return eventData;
	}

	@Override
	public EventType getEventType() {
		return eventType;
	}
	

}
