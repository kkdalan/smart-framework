package org.smart4j.framework.event.manager.impl;

import org.smart4j.framework.event.handler.impl.CustomerEventHandler;
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

}