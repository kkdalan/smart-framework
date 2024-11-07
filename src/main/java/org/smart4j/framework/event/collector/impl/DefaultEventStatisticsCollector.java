package org.smart4j.framework.event.collector.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.smart4j.framework.event.collector.EventStatisticsCollector;
import org.smart4j.framework.event.model.EventStatistics;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.model.MutableEventStatistics;

public class DefaultEventStatisticsCollector implements EventStatisticsCollector {

	private final Map<String, MutableEventStatistics> stats = new ConcurrentHashMap<>();

	// 生產端統計操作
	@Override
	public void incProducedEvents(EventType eventType) {
		statsFor(eventType).incProduced();
	}

	@Override
	public void incFailedProduction(EventType eventType) {
		statsFor(eventType).incProductionFailures();
	}

	@Override
	public void incProductionLatency(EventType eventType, long latencyNS) {
		statsFor(eventType).incProductionLatency(latencyNS);
	}

	// 消費端統計操作
	@Override
	public void incConsumedEvents(EventType eventType) {
		statsFor(eventType).incConsumed();
	}

	@Override
	public void incFailedConsumption(EventType eventType) {
		statsFor(eventType).incConsumptionFailures();
	}

	@Override
	public void incConsumptionLatency(EventType eventType, long latencyNS) {
		statsFor(eventType).incConsumptionLatency(latencyNS);
	}

	@Override
	public EventStatistics getEventStatistics(EventType eventType) {
		return statsFor(eventType).captureSnapshot();
	}

	private MutableEventStatistics statsFor(EventType eventType) {
		return stats.computeIfAbsent(eventType.getText(), MutableEventStatistics::new);
	}
}