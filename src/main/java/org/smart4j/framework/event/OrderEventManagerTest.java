package org.smart4j.framework.event;

import org.smart4j.framework.event.manager.impl.OrderEventManager;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventImpl;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.OrderStateMachine;

public class OrderEventManagerTest {

	public static void main(String[] args) {

		OrderEventManager orderManager = new OrderEventManager(new OrderStateMachine());

		// 模擬事件處理
		Event event = new EventImpl("OD0001", "訂單已建立事件", EventType.OrderCreated);
		try {

			// 事件發送
			orderManager.publishEvent(event);

			// 事件消費
			orderManager.consumeEvent(event);

			// 統計資訊
			orderManager.displayEventStatistics();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
