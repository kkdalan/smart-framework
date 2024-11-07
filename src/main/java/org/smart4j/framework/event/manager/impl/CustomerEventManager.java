package org.smart4j.framework.event.manager.impl;

import org.smart4j.framework.event.handler.impl.CustomerEventHandler;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.CustomerState;
import org.smart4j.framework.event.state.CustomerStateMachine;

public class CustomerEventManager extends AbstractStateEventManager<CustomerState> {

	public CustomerEventManager(CustomerStateMachine stateMachine) {
		super(stateMachine);
		registerEventHandler(EventType.CustomerDeleted, new CustomerEventHandler(stateMachine));
		registerEventHandler(EventType.CustomerUpdated, new CustomerEventHandler(stateMachine));
		registerEventHandler(EventType.CustomerRegistered, new CustomerEventHandler(stateMachine));
	}

	@Override
	public void publishEvent(Event event) throws Exception {
		System.out.println("發送客戶事件: " + event.getEventId());
		super.publishEvent(event);
	}

	@Override
	public void consumeEvent(Event event) throws Exception {
		System.out.println("處理客戶事件: " + event.getEventId());
		super.consumeEvent(event);
	}

	@Override
	public void displayEventStatistics() {
		System.out.println("客戶事件統計: ");
		super.displayEventStatistics();
	}

}