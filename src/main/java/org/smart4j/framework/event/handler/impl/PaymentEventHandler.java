package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.state.PaymentState;
import org.smart4j.framework.event.state.PaymentStateMachine;

public class PaymentEventHandler extends AbstractEventHandler<PaymentState> {

	public PaymentEventHandler(PaymentStateMachine stateMachine) {
		super(stateMachine);
		currentState = PaymentState.PENDING;
	}

	@Override
	protected void triggerNextEvent(PaymentState state) {
		// 模擬根據 PaymentState 狀態發佈下一步的事件
		switch (state) {
		case PENDING:
			System.out.println("觸發事件: PaymentInitiated");
			break;
		case INITIATED:
			System.out.println("觸發事件: PaymentProcessing");
			break;
		case PROCESSING:
			System.out.println("觸發事件: PaymentCompleted");
			break;
		case COMPLETED:
			System.out.println("付款已完成，觸發下一步處理");
			break;
		case FAILED:
			System.out.println("觸發事件: PaymentFailed");
			break;
		case REFUNDED:
			System.out.println("觸發事件: PaymentRefunded");
			break;
		default:
			System.out.println("未處理的狀態: " + state);
			break;
		}
	}

}
