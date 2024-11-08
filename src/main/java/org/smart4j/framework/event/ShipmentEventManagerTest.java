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

		// 出貨相關事件
		Event event = new EventImpl("SH0001", "出貨安排", EventType.ShipmentScheduled);
		event = new EventImpl("SH0002", "出貨中", EventType.ShipmentInTransit);
		event = new EventImpl("SH0003", "出貨已送達", EventType.ShipmentDelivered);
		event = new EventImpl("SH0004", "出貨延遲", EventType.ShipmentDelayed);
		event = new EventImpl("SH0005", "出貨丟失", EventType.ShipmentLost);

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
