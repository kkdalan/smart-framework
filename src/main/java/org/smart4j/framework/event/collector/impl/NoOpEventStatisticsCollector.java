package org.smart4j.framework.event.collector.impl;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import org.smart4j.framework.event.collector.EventStatisticsCollector;
import org.smart4j.framework.event.model.EventStatistics;
import org.smart4j.framework.event.model.EventType;

/**
 * {@link EventStatisticsCollector} implementation that does not capture any
 * event statistics and throws an {@link IllegalStateException} when attempting
 * to obtain statistics for an event type.
 * 
 * This implementation is a no-operation singleton instance.
 * 
 * @since 2.4
 */
public enum NoOpEventStatisticsCollector implements EventStatisticsCollector {

	INSTANCE;

	@Override
	public void incProducedEvents(EventType eventType) {
	}

	@Override
	public void incFailedProduction(EventType eventType) {

	}

	@Override
	public void incProductionLatency(EventType eventType, long latencyNS) {

	}

	@Override
	public void incConsumedEvents(EventType eventType) {

	}

	@Override
	public void incFailedConsumption(EventType eventType) {

	}

	@Override
	public void incConsumptionLatency(EventType eventType, long latencyNS) {

	}

	@Override
	public EventStatistics getEventStatistics(EventType eventType) {
		return new EmptyStatistics(eventType.getText());
	}

	/**
	 * A no-operation {@link EventStatistics} implementation that returns default
	 * values for all statistics.
	 */
	private static class EmptyStatistics implements EventStatistics {

		private final String eventType;

		EmptyStatistics(String eventType) {
			this.eventType = eventType;
		}

		@Override
		public String getEventType() {
			return eventType;
		}

		@Override
		public long getProduced() {
			return 0;
		}

		@Override
		public long getProductionFailures() {
			return 0;
		}

		@Override
		public long getProducedLatency(TimeUnit unit) {
			return 0;
		}

		@Override
		public long getConsumed() {
			return 0;
		}

		@Override
		public long getConsumptionFailures() {
			return 0;
		}

		@Override
		public long getConsumedLatency(TimeUnit unit) {
			return 0;
		}

		@Override
		public Instant getAliveSince() {
			return Instant.EPOCH;
		}

		@Override
		public Instant getLastReset() {
			return getAliveSince();
		}

		@Override
		public boolean equals(@Nullable Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			EmptyStatistics that = (EmptyStatistics) o;
			return Objects.equals(eventType, that.eventType);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(eventType);
		}

		@Override
		public String getFormattedStatistics() {
			return "<none>";
		}
	}
}
