package org.smart4j.framework.event.state;

import org.smart4j.framework.event.model.EventType;

// 客戶狀態機
public class CustomerStateMachine extends StateMachine<CustomerState, EventType> {

	public CustomerStateMachine() {
		super(CustomerState.NEW); // 初始狀態

		this.addTransition(CustomerState.NEW, EventType.CustomerRegistered, CustomerState.ACTIVE);
		this.addTransition(CustomerState.ACTIVE, EventType.CustomerUpdated, CustomerState.UPDATED);
		this.addTransition(CustomerState.UPDATED, EventType.CustomerDeleted, CustomerState.DELETED);
	}
}
