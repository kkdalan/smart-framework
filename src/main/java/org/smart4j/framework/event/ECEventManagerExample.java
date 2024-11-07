package org.smart4j.framework.event;

import org.smart4j.framework.event.manager.impl.CustomerEventManager;
import org.smart4j.framework.event.manager.impl.OrderEventManager;
import org.smart4j.framework.event.manager.impl.PaymentEventManager;
import org.smart4j.framework.event.manager.impl.ShipmentEventManager;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventImpl;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.CustomerStateMachine;
import org.smart4j.framework.event.state.OrderStateMachine;
import org.smart4j.framework.event.state.PaymentStateMachine;
import org.smart4j.framework.event.state.ShipmentStateMachine;

public class ECEventManagerExample {

	public static void main(String[] args) {

		CustomerEventManager customerManager = new CustomerEventManager(new CustomerStateMachine());
		OrderEventManager orderManager = new OrderEventManager(new OrderStateMachine());
		PaymentEventManager paymentManager = new PaymentEventManager(new PaymentStateMachine());
		ShipmentEventManager shipmentManager = new ShipmentEventManager(new ShipmentStateMachine());

		// 模擬事件處理
		Event event = new EventImpl("0001", "準備付款", EventType.PaymentReady);
		try {
			
			// 事件發送
//			customerManager.publishEvent(event);
//			orderManager.publishEvent(event);
			paymentManager.publishEvent(event);
//			shipmentManager.publishEvent(event);
			
			// 事件消費
//			customerManager.consumeEvent(event);
//			orderManager.consumeEvent(event);
			paymentManager.consumeEvent(event);
//			shipmentManager.consumeEvent(event);

			// 統計資訊
//			customerManager.displayEventStatistics();
//			orderManager.displayEventStatistics();
			paymentManager.displayEventStatistics();
//			shipmentManager.displayEventStatistics();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
