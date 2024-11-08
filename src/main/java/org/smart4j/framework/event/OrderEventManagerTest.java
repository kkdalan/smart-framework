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

		// 訂單相關事件
		Event event = new EventImpl("ORD001", "訂單創建", EventType.OrderCreated);
		event = new EventImpl("ORD002", "訂單確認", EventType.OrderConfirmed);
		event = new EventImpl("ORD003", "訂單出貨", EventType.OrderShipped);
		event = new EventImpl("ORD004", "訂單送達", EventType.OrderDelivered);
		event = new EventImpl("ORD005", "訂單取消", EventType.OrderCancelled);
		event = new EventImpl("ORD006", "訂單退貨", EventType.OrderReturned);

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
