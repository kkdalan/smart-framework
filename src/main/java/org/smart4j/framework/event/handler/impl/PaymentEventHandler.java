package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.PaymentState;
import org.smart4j.framework.event.state.PaymentStateMachine;

public class PaymentEventHandler implements EventHandler {

	private final PaymentStateMachine stateMachine;
	private PaymentState currentState = PaymentState.PENDING;

	public PaymentEventHandler(PaymentStateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	
	@Override
	public void publishEvent(Event event) {
		System.out.println("發送事件: " + event.getEventId() + ", 當前狀態: " + currentState);
	}

	@Override
	public void consumeEvent(Event event) {
		try {
			EventType eventType = event.getEventType();
			System.out.println("接收事件: " + event.getEventId() + ", 當前狀態: " + currentState);
			PaymentState nextState = stateMachine.getNextState(currentState, eventType);
			System.out.println("狀態轉換: " + currentState + " -> " + nextState + " 事件: " + eventType);

			// 更新當前狀態
			currentState = nextState;

			// 根據新的狀態發佈下一個事件（模擬觸發下一步流程）
			triggerNextEvent(nextState);

		} catch (IllegalStateException e) {
			System.out.println("無法處理事件: " + e.getMessage());
		}
	}

	private void triggerNextEvent(PaymentState state) {
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
