package org.smart4j.framework.event.manager.impl;

import org.smart4j.framework.event.handler.impl.OrderEventHandler;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.OrderState;
import org.smart4j.framework.event.state.OrderStateMachine;

public class OrderEventManager extends AbstractStateEventManager<OrderState> {

	public OrderEventManager(OrderStateMachine stateMachine) {
		super(stateMachine);
		registerEventHandler(EventType.OrderCreated, new OrderEventHandler(stateMachine));
		registerEventHandler(EventType.OrderCancelled, new OrderEventHandler(stateMachine));
		registerEventHandler(EventType.OrderConfirmed, new OrderEventHandler(stateMachine));
		registerEventHandler(EventType.OrderDelivered, new OrderEventHandler(stateMachine));
		registerEventHandler(EventType.OrderReturned, new OrderEventHandler(stateMachine));
		registerEventHandler(EventType.OrderShipped, new OrderEventHandler(stateMachine));
	}

	@Override
	public void publishEvent(Event event) throws Exception {
		System.out.println("發送訂單事件: " + event.getEventId());
		super.publishEvent(event);
	}

	@Override
	public void consumeEvent(Event event) throws Exception {
		System.out.println("處理訂單事件: " + event.getEventId());
		super.consumeEvent(event);
	}

	@Override
	public void displayEventStatistics() {
		System.out.println("訂單事件統計: ");
		super.displayEventStatistics();
	}

}
