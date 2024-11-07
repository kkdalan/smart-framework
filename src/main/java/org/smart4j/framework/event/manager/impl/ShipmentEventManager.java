package org.smart4j.framework.event.manager.impl;

import org.smart4j.framework.event.handler.impl.ShipmenetEventHandler;
import org.smart4j.framework.event.model.Event;
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

	@Override
	public void publishEvent(Event event) throws Exception {
		System.out.println("發送出貨事件: " + event.getEventId());
		super.publishEvent(event);
	}

	@Override
	public void consumeEvent(Event event) throws Exception {
		System.out.println("處理出貨事件: " + event.getEventId());
		super.consumeEvent(event);

	}

	@Override
	public void displayEventStatistics() {
		System.out.println("出貨事件統計: ");
		super.displayEventStatistics();
	}

}
