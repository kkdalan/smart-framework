package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.CustomerState;
import org.smart4j.framework.event.state.CustomerStateMachine;

public class CustomerEventHandler implements EventHandler {

	private final CustomerStateMachine stateMachine;
	private CustomerState currentState = CustomerState.NEW;

	public CustomerEventHandler(CustomerStateMachine stateMachine) {
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
			CustomerState nextState = stateMachine.getNextState(currentState, eventType);
			System.out.println("狀態轉換: " + currentState + " -> " + nextState + " 事件: " + eventType);

			// 更新當前狀態
			currentState = nextState;

			// 根據新的狀態發佈下一個事件（模擬觸發下一步流程）
			triggerNextEvent(nextState);

		} catch (IllegalStateException e) {
			System.out.println("無法處理事件: " + e.getMessage());
		}
	}

	private void triggerNextEvent(CustomerState state) {
		// 根據 CustomerState 狀態發佈下一步的事件
		switch (state) {
			case NEW:
				System.out.println("觸發事件: CustomerRegistered");
				break;
			case ACTIVE:
				System.out.println("觸發事件: CustomerUpdated");
				break;
			case UPDATED:
				System.out.println("觸發事件: CustomerUpdated");
				break;
			case DELETED:
				System.out.println("觸發事件: CustomerDeleted");
				break;
			default:
				System.out.println("未處理的狀態: " + state);
				break;
		}
	}

}
