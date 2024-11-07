package org.smart4j.framework.event.model;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public interface EventStatistics {
	
	String getEventType();

	long getProduced();

	long getProductionFailures();

	long getProducedLatency(TimeUnit unit);

	long getConsumed();

	long getConsumptionFailures();

	long getConsumedLatency(TimeUnit unit);

	Instant getAliveSince();

	Instant getLastReset();
	
    String getFormattedStatistics();
}