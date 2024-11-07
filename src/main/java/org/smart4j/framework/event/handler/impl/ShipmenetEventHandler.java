package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.ShipmentState;
import org.smart4j.framework.event.state.ShipmentStateMachine;

public class ShipmenetEventHandler implements EventHandler {

	private final ShipmentStateMachine stateMachine;
	private ShipmentState currentState = ShipmentState.SCHEDULED;

	public ShipmenetEventHandler(ShipmentStateMachine stateMachine) {
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
			ShipmentState nextState = stateMachine.getNextState(currentState, eventType);
			System.out.println("狀態轉換: " + currentState + " -> " + nextState + " 事件: " + eventType);

			// 更新當前狀態
			currentState = nextState;

			// 根據新的狀態發佈下一個事件（模擬觸發下一步流程）
			triggerNextEvent(nextState);

		} catch (IllegalStateException e) {
			System.out.println("無法處理事件: " + e.getMessage());
		}
	}

	private void triggerNextEvent(ShipmentState state) {
		// 根據 ShipmentState 狀態發佈下一步的事件
		switch (state) {
			case SCHEDULED:
				System.out.println("觸發事件: ShipmentInTransit");
				break;
			case IN_TRANSIT:
				System.out.println("觸發事件: ShipmentDelivered");
				break;
			case DELIVERED:
				System.out.println("觸發事件: OrderDelivered");
				break;
			case DELAYED:
				System.out.println("觸發事件: ShipmentDelayed");
				break;
			case LOST:
				System.out.println("觸發事件: ShipmentLost");
				break;
			default:
				System.out.println("未處理的狀態: " + state);
				break;
		}
	}

}
