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

		// 付款相關事件
		Event event = new EventImpl("PAY001", "付款啟動", EventType.PaymentReady);
		event = new EventImpl("PAY002", "付款完成", EventType.PaymentCompleted);
		event = new EventImpl("PAY003", "付款失敗", EventType.PaymentFailed);
		event = new EventImpl("PAY004", "付款退款", EventType.PaymentRefunded);

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
