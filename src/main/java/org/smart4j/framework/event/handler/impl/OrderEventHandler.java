package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.OrderState;
import org.smart4j.framework.event.state.OrderStateMachine;

public class OrderEventHandler implements EventHandler {

	private final OrderStateMachine stateMachine;
	private OrderState currentState;

	public OrderEventHandler(OrderStateMachine stateMachine) {
		this.stateMachine = stateMachine;
		this.currentState = stateMachine.getCurrentState();
	}

	@Override
	public void publishEvent(Event event) {
		System.out.println("發送事件: " + event.getEventId() + ", 當前狀態: " + currentState);
		// 根據新的狀態發佈下一個事件（模擬觸發下一步流程）
		triggerNextEvent(currentState);
	}
	
	@Override
	public void consumeEvent(Event event) {
		try {

			EventType eventType = event.getEventType();
			System.out.println("接收事件: " + event.getEventId() + ", 當前狀態: " + currentState);

			OrderState nextState = stateMachine.getNextState(currentState, eventType);
			System.out.println("狀態轉換: [" + currentState + " -> " + nextState + "], 事件: [" + eventType + "]");

			// 更新當前狀態
			currentState = nextState;

		} catch (IllegalStateException e) {
			System.out.println("無法處理事件: " + e.getMessage());
		}
	}

	private void triggerNextEvent(OrderState state) {
		// 根據 OrderState 狀態發佈下一步的事件
		switch (state) {
		case NEW:
			System.out.println("觸發事件: OrderCreated");
			break;
		case CREATED:
			System.out.println("觸發事件: OrderConfirmed");
			break;
		case CONFIRMED:
			System.out.println("觸發事件: PaymentInitiated");
			break;
		case PAYING:
			System.out.println("觸發事件: PaymentProcessing");
			break;
		case PAID:
			System.out.println("觸發事件: ShipmentScheduled");
			break;
		case SHIPPING:
			System.out.println("觸發事件: ShipmentInTransit");
			break;
		case SHIPPED:
			System.out.println("觸發事件: ShipmentInTransit"); // 因為 SHIPPED 轉移到 DELIVERYING
			break;
		case DELIVERYING:
			System.out.println("觸發事件: ShipmentDelivered"); // 訂單配送完成
			break;
		case DELIVERED:
			System.out.println("訂單已完成交付");
			break;
		case FAILED:
			System.out.println("觸發事件: ShipmentLost"); // 配送失敗
			break;
		case CANCELLED:
			System.out.println("觸發事件: OrderCancelled");
			break;
		case RETURNED:
			System.out.println("觸發事件: OrderReturned");
			break;
		default:
			System.out.println("未處理的狀態: " + state);
			break;
		}
	}

}
