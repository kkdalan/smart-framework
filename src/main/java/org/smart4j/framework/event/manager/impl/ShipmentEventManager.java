package org.smart4j.framework.event.manager.impl;

import org.smart4j.framework.event.handler.impl.ShipmenetEventHandler;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.ShipmentState;
import org.smart4j.framework.event.state.ShipmentStateMachine;

public class ShipmentEventManager extends AbstractStateEventManager<ShipmentState> {

	public ShipmentEventManager(ShipmentStateMachine stateMachine) {
		super(stateMachine);
		registerEventHandler(EventType.ShipmentDelayed, new ShipmenetEventHandler(stateMachine));
		registerEventHandler(EventType.ShipmentDelivered, new ShipmenetEventHandler(stateMachine));
		registerEventHandler(EventType.ShipmentInTransit, new ShipmenetEventHandler(stateMachine));
		registerEventHandler(EventType.ShipmentLost, new ShipmenetEventHandler(stateMachine));
		registerEventHandler(EventType.ShipmentScheduled, new ShipmenetEventHandler(stateMachine));
	}

}
