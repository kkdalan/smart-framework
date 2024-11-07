package org.smart4j.framework.event.manager.impl;

import org.smart4j.framework.event.handler.impl.PaymentEventHandler;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.PaymentState;
import org.smart4j.framework.event.state.PaymentStateMachine;

public class PaymentEventManager extends AbstractStateEventManager<PaymentState> {

	public PaymentEventManager(PaymentStateMachine stateMachine) {
		super(stateMachine);
		registerEventHandler(EventType.PaymentReady, new PaymentEventHandler(stateMachine));
		registerEventHandler(EventType.PaymentCompleted, new PaymentEventHandler(stateMachine));
		registerEventHandler(EventType.PaymentFailed, new PaymentEventHandler(stateMachine));
		registerEventHandler(EventType.PaymentRefunded, new PaymentEventHandler(stateMachine));
	}

	@Override
	public void publishEvent(Event event) throws Exception {
		System.out.println("發送支付事件: " + event.getEventId());
		super.publishEvent(event);
	}

	@Override
	public void consumeEvent(Event event) throws Exception {
		System.out.println("處理支付事件: " + event.getEventId());
		super.consumeEvent(event);
	}

	@Override
	public void displayEventStatistics() {
		System.out.println("支付事件統計: ");
		super.displayEventStatistics();
	}

}
