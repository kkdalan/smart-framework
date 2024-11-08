package org.smart4j.framework.event.model;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public interface EventStatistics {
	
	String getEventType();

	long getProduceSuccess();

	long getProduceFailure();

	long getProduceLatency(TimeUnit unit);

	long getConsumeSuccess();

	long getConsumeFailure();

	long getConsumeLatency(TimeUnit unit);

	Instant getAliveSince();

	Instant getLastReset();
	
    String getFormattedStatistics();
}