package org.smart4j.framework.event;

import org.smart4j.framework.event.manager.impl.PaymentEventManager;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventImpl;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.PaymentStateMachine;

public class PaymentEventManagerTest {

	public static void main(String[] args) {

		PaymentEventManager paymentManager = new PaymentEventManager(new PaymentStateMachine());

		// 模擬事件處理
		Event event = new EventImpl("PA0001", "準備付款", EventType.PaymentReady);
		try {
			
			// 事件發送
			paymentManager.publishEvent(event);
			
			// 事件消費
			paymentManager.consumeEvent(event);

			// 統計資訊
			paymentManager.displayEventStatistics();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
