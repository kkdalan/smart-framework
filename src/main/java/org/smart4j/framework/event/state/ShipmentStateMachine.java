package org.smart4j.framework.event.state;

import org.smart4j.framework.event.model.EventType;

// 出貨狀態機
public class ShipmentStateMachine extends StateMachine<ShipmentState, EventType> {

	public ShipmentStateMachine() {
		super(ShipmentState.SCHEDULED); // 出貨的初始狀態

		this.addTransition(ShipmentState.SCHEDULED, EventType.ShipmentScheduled, ShipmentState.IN_TRANSIT);
		this.addTransition(ShipmentState.IN_TRANSIT, EventType.ShipmentDelivered, ShipmentState.DELIVERED);
		this.addTransition(ShipmentState.IN_TRANSIT, EventType.ShipmentDelayed, ShipmentState.DELAYED);
		this.addTransition(ShipmentState.IN_TRANSIT, EventType.ShipmentLost, ShipmentState.LOST);
	}
}
