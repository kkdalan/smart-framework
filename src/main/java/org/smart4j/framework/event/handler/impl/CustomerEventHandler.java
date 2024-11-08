package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.state.CustomerState;
import org.smart4j.framework.event.state.CustomerStateMachine;

public class CustomerEventHandler extends AbstractEventHandler<CustomerState> {

	public CustomerEventHandler(CustomerStateMachine stateMachine) {
		super(stateMachine);
		this.currentState = CustomerState.NEW;
	}

	@Override
	protected void triggerNextEvent(CustomerState state) {
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
