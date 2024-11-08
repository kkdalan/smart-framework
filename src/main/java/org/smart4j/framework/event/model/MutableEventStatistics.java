package org.smart4j.framework.event.model;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class MutableEventStatistics implements EventStatistics {

	private final String eventType;
	private final Instant aliveSince = Instant.now();
	private Instant lastReset = aliveSince;

	private final LongAdder produced = new LongAdder();
	private final LongAdder productionFailures = new LongAdder();
	private final LongAdder producedLatency = new LongAdder();

	private final LongAdder consumed = new LongAdder();
	private final LongAdder consumptionFailures = new LongAdder();
	private final LongAdder consumedLatency = new LongAdder();

	public MutableEventStatistics(String eventType) {
		this.eventType = eventType;
	}

	public void incProduced() {
		produced.increment();
	}

	public void incProductionFailures() {
		productionFailures.increment();
	}

	public void incProductionLatency(long latencyNS) {
		producedLatency.add(latencyNS);
	}

	public void incConsumed() {
		consumed.increment();
	}

	public void incConsumptionFailures() {
		consumptionFailures.increment();
	}

	public void incConsumptionLatency(long latencyNS) {
		consumedLatency.add(latencyNS);
	}

	public EventStatistics captureSnapshot() {
		return new Snapshot(this);
	}

	@Override
	public String getEventType() {
		return eventType;
	}

	@Override
	public long getProduceSuccess() {
		return produced.sum();
	}

	@Override
	public long getProduceFailure() {
		return productionFailures.sum();
	}

	@Override
	public long getProduceLatency(TimeUnit unit) {
		return unit.convert(producedLatency.sum(), TimeUnit.NANOSECONDS);
	}

	@Override
	public long getConsumeSuccess() {
		return consumed.sum();
	}

	@Override
	public long getConsumeFailure() {
		return consumptionFailures.sum();
	}

	@Override
	public long getConsumeLatency(TimeUnit unit) {
		return unit.convert(consumedLatency.sum(), TimeUnit.NANOSECONDS);
	}

	@Override
	public Instant getAliveSince() {
		return aliveSince;
	}

	@Override
	public Instant getLastReset() {
		return lastReset;
	}

	// 快照類別，保持不可變性
	private static class Snapshot implements EventStatistics {
		private final String eventType;
		private final long produced;
		private final long productionFailures;
		private final long producedLatency;
		private final long consumed;
		private final long consumptionFailures;
		private final long consumedLatency;
		private final Instant aliveSince;
		private final Instant lastReset;

		Snapshot(MutableEventStatistics stats) {
			this.eventType = stats.eventType;
			this.produced = stats.produced.sum();
			this.productionFailures = stats.productionFailures.sum();
			this.producedLatency = stats.producedLatency.sum();
			this.consumed = stats.consumed.sum();
			this.consumptionFailures = stats.consumptionFailures.sum();
			this.consumedLatency = stats.consumedLatency.sum();
			this.aliveSince = stats.aliveSince;
			this.lastReset = stats.lastReset;
		}

		@Override
		public String getEventType() {
			return eventType;
		}

		@Override
		public long getProduceSuccess() {
			return produced;
		}

		@Override
		public long getProduceFailure() {
			return productionFailures;
		}

		@Override
		public long getProduceLatency(TimeUnit unit) {
			return unit.convert(producedLatency, TimeUnit.NANOSECONDS);
		}

		@Override
		public long getConsumeSuccess() {
			return consumed;
		}

		@Override
		public long getConsumeFailure() {
			return consumptionFailures;
		}

		@Override
		public long getConsumeLatency(TimeUnit unit) {
			return unit.convert(consumedLatency, TimeUnit.NANOSECONDS);
		}

		@Override
		public Instant getAliveSince() {
			return aliveSince;
		}

		@Override
		public Instant getLastReset() {
			return lastReset;
		}
		
		@Override
	    public String getFormattedStatistics() {
	        return String.format(
	                "EventType: %s\nProduced: %d\nProductionFailures: %d\nProducedLatency: %d\n" +
	                "Consumed: %d\nConsumptionFailures: %d\nConsumedLatency: %d\n" +
	                "AliveSince: %s\nLastReset: %s",
	                eventType, produced, productionFailures, producedLatency,
	                consumed, consumptionFailures, consumedLatency,
	                aliveSince.toString(), lastReset.toString());
	    }

	}
	
	@Override
    public String getFormattedStatistics() {
        return String.format(
                "EventType: %s\nProduced: %d\nProductionFailures: %d\nProducedLatency: %d\n" +
                "Consumed: %d\nConsumptionFailures: %d\nConsumedLatency: %d\n" +
                "AliveSince: %s\nLastReset: %s",
                eventType, produced.longValue(), productionFailures.longValue(), producedLatency.longValue(),
                consumed.longValue(), consumptionFailures.longValue(), consumedLatency.longValue(),
                aliveSince.toString(), lastReset.toString());
    }
}
