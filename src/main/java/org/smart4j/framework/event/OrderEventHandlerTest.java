package org.smart4j.framework.event;

import org.smart4j.framework.event.handler.impl.OrderEventHandler;
import org.smart4j.framework.event.model.EventImpl;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.OrderStateMachine;

public class OrderEventHandlerTest {

    public static void main(String[] args) {

    	OrderStateMachine stateMachine = new OrderStateMachine();
        OrderEventHandler orderEventHandler = new OrderEventHandler(stateMachine);

        orderEventHandler.publishEvent(new EventImpl("1", "訂單已建立事件", EventType.OrderCreated));
        orderEventHandler.publishEvent(new EventImpl("2", "訂單已確認事件", EventType.OrderConfirmed));
        orderEventHandler.publishEvent(new EventImpl("3", "付款已就緒事件", EventType.PaymentReady));
        orderEventHandler.publishEvent(new EventImpl("4", "付款已完成事件", EventType.PaymentCompleted));
    }
}
