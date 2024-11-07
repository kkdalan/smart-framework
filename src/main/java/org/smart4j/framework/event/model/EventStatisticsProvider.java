package org.smart4j.framework.event.model;

public interface EventStatisticsProvider {

	EventStatistics getEventStatistics(EventType eventType);
}
