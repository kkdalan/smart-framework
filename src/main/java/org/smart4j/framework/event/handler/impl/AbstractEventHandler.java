package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.State;
import org.smart4j.framework.event.state.StateMachine;

public abstract class AbstractEventHandler<S extends State> implements EventHandler {

	protected final StateMachine<S, EventType> stateMachine;
	protected S currentState;

	public AbstractEventHandler(StateMachine<S, EventType> stateMachine) {
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
			
			System.out.print("接收事件: " + event.getEventId() + ", 當前狀態: " + currentState);
			S nextState = stateMachine.getNextState(currentState, eventType);
			nextState = (nextState != null) ? nextState : getErrorState();
			System.out.println("  狀態轉換: " + currentState + " -> " + nextState + " 事件: " + eventType);

			// 更新當前狀態
			currentState = nextState;

			// 根據新的狀態發佈下一個事件（模擬觸發下一步流程）
			triggerNextEvent(nextState);

		} catch (IllegalStateException e) {
			System.out.println("無法處理事件: " + e.getMessage());
		}
	}

	protected abstract void triggerNextEvent(S state);
	
	protected abstract S getErrorState();
	
	

}
