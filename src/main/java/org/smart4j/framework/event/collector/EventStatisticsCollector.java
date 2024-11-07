package org.smart4j.framework.event.collector;

import org.smart4j.framework.event.collector.impl.DefaultEventStatisticsCollector;
import org.smart4j.framework.event.collector.impl.NoOpEventStatisticsCollector;
import org.smart4j.framework.event.model.EventStatisticsProvider;
import org.smart4j.framework.event.model.EventType;

public interface EventStatisticsCollector extends EventStatisticsProvider {

	// 生產端統計操作
	void incProducedEvents(EventType eventType);

	void incFailedProduction(EventType eventType);

	void incProductionLatency(EventType eventType, long latencyNS);

	// 消費端統計操作
	void incConsumedEvents(EventType eventType);

	void incFailedConsumption(EventType eventType);

	void incConsumptionLatency(EventType eventType, long latencyNS);

	/**
	 * @return a {@link CacheStatisticsCollector} that performs no action.
	 */
	static EventStatisticsCollector none() {
		return NoOpEventStatisticsCollector.INSTANCE;
	}

	/**
	 * @return a default {@link CacheStatisticsCollector} implementation.
	 */
	static EventStatisticsCollector create() {
		return new DefaultEventStatisticsCollector();
	}
}