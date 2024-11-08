package org.smart4j.framework.event.manager.impl;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.smart4j.framework.event.collector.EventStatisticsCollector;
import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.handler.impl.UnsupportedEventHandler;
import org.smart4j.framework.event.manager.EventManager;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventStatistics;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.State;
import org.smart4j.framework.event.state.StateMachine;
import org.smart4j.framework.util.ds.table.DataRow;
import org.smart4j.framework.util.ds.table.DataTable;

public class AbstractStateEventManager<S extends State> implements EventManager {

	// 儲存事件處理器
	private final Map<EventType, EventHandler> eventHandlers = new ConcurrentHashMap<>();
	// 統計收集器
	private final EventStatisticsCollector statisticsCollector = EventStatisticsCollector.create();
	// 事件處理執行器，用於非同步處理事件
	private final ExecutorService executorService = Executors.newCachedThreadPool();

	protected final StateMachine<S, EventType> stateMachine;

	public AbstractStateEventManager(StateMachine<S, EventType> stateMachine) {
		Objects.requireNonNull(stateMachine, "stateMachine cannot be null.");
		this.stateMachine = stateMachine;
	}

	// 註冊事件處理器
	@Override
	public void registerEventHandler(EventType eventType, EventHandler eventHandler) {
		eventHandlers.put(eventType, eventHandler);
	}

	// 發佈事件
	@Override
	public void publishEvent(Event event) throws Exception {
		EventType eventType = event.getEventType();
		EventHandler handler = getEventHandler(eventType);
		executorService.submit(() -> {
			try {
				long startTime = System.nanoTime();
				handler.publishEvent(event);
				long endTime = System.nanoTime();
				statisticsCollector.incProducedEvents(eventType);
				statisticsCollector.incProductionLatency(eventType, endTime - startTime);
			} catch (Exception e) {
				statisticsCollector.incFailedProduction(eventType);
			}
		});
	}

	// 消費事件
	@Override
	public void consumeEvent(Event event) throws Exception {
		EventType eventType = event.getEventType();
		EventHandler handler = getEventHandler(eventType);
		executorService.submit(() -> {
			try {
				long startTime = System.nanoTime();
				handler.consumeEvent(event);
				long endTime = System.nanoTime();
				statisticsCollector.incConsumedEvents(eventType);
				statisticsCollector.incConsumptionLatency(eventType, endTime - startTime);
			} catch (Exception e) {
				statisticsCollector.incFailedConsumption(eventType);
			}
		});
	}

	private EventHandler getEventHandler(EventType eventType) {
		return eventHandlers.getOrDefault(eventType, UnsupportedEventHandler.getInstance());
	}

	// 獲取事件統計資料
	@Override
	public EventStatistics getEventStatistics(EventType eventType) {
		return statisticsCollector.getEventStatistics(eventType);
	}

	@Override
	public void displayEventStatistics() {

		DataTable dt = new DataTable("事件統計");

		dt.columns().add("事件類型");
		dt.columns().add("發送成功");
		dt.columns().add("發送失敗");
		dt.columns().add("發送延遲");
		dt.columns().add("消費成功");
		dt.columns().add("消費失敗");
		dt.columns().add("消費延遲");
		dt.columns().add("存活時間");
		dt.columns().add("最後重設");

		eventHandlers.keySet().forEach(type -> {

		    EventStatistics stat = statisticsCollector.getEventStatistics(type);

		    DataRow dr = dt.newRow();
		    dr.setValue("事件類型", stat.getEventType());
		    dr.setValue("發送成功", stat.getProduceSuccess());
		    dr.setValue("發送失敗", stat.getProduceFailure());
		    dr.setValue("發送延遲", stat.getProduceLatency(TimeUnit.MILLISECONDS));
		    dr.setValue("消費成功", stat.getConsumeSuccess());
		    dr.setValue("消費失敗", stat.getConsumeFailure());
		    dr.setValue("消費延遲", stat.getConsumeLatency(TimeUnit.MILLISECONDS));
		    dr.setValue("存活時間", stat.getAliveSince());
		    dr.setValue("最後重設", stat.getLastReset());

		    dt.rows().add(dr);
		});



		dt.print();

	}

	// 關閉執行器
	@Override
	public void shutdown() {
		executorService.shutdown();
	}

}
