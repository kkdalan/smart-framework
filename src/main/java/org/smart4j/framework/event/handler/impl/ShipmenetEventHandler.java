package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.state.ShipmentState;
import org.smart4j.framework.event.state.ShipmentStateMachine;

public class ShipmenetEventHandler extends AbstractEventHandler<ShipmentState> {

	public ShipmenetEventHandler(ShipmentStateMachine stateMachine) {
		super(stateMachine);
		this.currentState = ShipmentState.SCHEDULED;
	}

	@Override
	protected void triggerNextEvent(ShipmentState state) {
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
	
	@Override
	protected ShipmentState getErrorState() {
		return ShipmentState.ERROR;
	}

}
