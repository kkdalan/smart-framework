package org.smart4j.framework.event.state;

import org.smart4j.framework.event.model.EventType;

// 付款狀態機
public class PaymentStateMachine extends StateMachine<PaymentState, EventType> {

	public PaymentStateMachine() {
		super(PaymentState.PENDING); // 付款的初始狀態

		this.addTransition(PaymentState.PENDING, EventType.PaymentReady, PaymentState.PROCESSING);
		this.addTransition(PaymentState.PROCESSING, EventType.PaymentCompleted, PaymentState.COMPLETED);
		this.addTransition(PaymentState.PROCESSING, EventType.PaymentFailed, PaymentState.FAILED);
		this.addTransition(PaymentState.COMPLETED, EventType.PaymentRefunded, PaymentState.REFUNDED);
	}
}
