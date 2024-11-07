package org.smart4j.framework.event.manager;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventStatistics;
import org.smart4j.framework.event.model.EventType;

public interface EventManager {

	// 註冊事件處理器
	void registerEventHandler(EventType eventType, EventHandler eventHandler);

	// 發佈事件
	void publishEvent(Event event) throws Exception;

	// 消費事件
	void consumeEvent(Event event) throws Exception;

	// 獲取事件統計資料
	EventStatistics getEventStatistics(EventType eventType);

	// 印出事件統計資料
	void displayEventStatistics();

	// 關閉執行器
	void shutdown();

}