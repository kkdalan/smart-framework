package org.smart4j.framework.event;

import org.smart4j.framework.event.manager.impl.ShipmentEventManager;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventImpl;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.ShipmentStateMachine;

public class ShipmentEventManagerTest {

	public static void main(String[] args) {

		ShipmentEventManager shipmentManager = new ShipmentEventManager(new ShipmentStateMachine());

		// 模擬事件處理
		Event event = new EventImpl("SH0001", "出貨已安排", EventType.ShipmentScheduled);
		try {
			
			// 事件發送
			shipmentManager.publishEvent(event);
			
			// 事件消費
			shipmentManager.consumeEvent(event);

			// 統計資訊
			shipmentManager.displayEventStatistics();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
