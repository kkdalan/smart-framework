package org.smart4j.framework.event.state;

import org.smart4j.framework.event.model.EventType;

// 訂單狀態機
public class OrderStateMachine extends StateMachine<OrderState, EventType> {

    public OrderStateMachine() {
        super(OrderState.NEW); // 訂單的初始狀態
        
        // 訂單建立
        this.addTransition(OrderState.NEW, EventType.OrderCreated, OrderState.CREATED);

        // 訂單確認
        this.addTransition(OrderState.CREATED, EventType.OrderConfirmed, OrderState.CONFIRMED);

        // 支付進行中
        this.addTransition(OrderState.CONFIRMED, EventType.PaymentReady, OrderState.PAYING);

        // 支付完成
        this.addTransition(OrderState.PAYING, EventType.PaymentCompleted, OrderState.PAID);

        // 出貨安排
        this.addTransition(OrderState.PAID, EventType.ShipmentScheduled, OrderState.SHIPPING);

        // 訂單已出貨
        this.addTransition(OrderState.SHIPPING, EventType.ShipmentInTransit, OrderState.SHIPPED);

        // 配送中
        this.addTransition(OrderState.SHIPPED, EventType.ShipmentInTransit, OrderState.DELIVERYING);

        // 配送完成
        this.addTransition(OrderState.DELIVERYING, EventType.ShipmentDelivered, OrderState.DELIVERED);

        // 配送失敗
        this.addTransition(OrderState.DELIVERYING, EventType.ShipmentLost, OrderState.FAILED);

        // 訂單取消
        this.addTransition(OrderState.CREATED, EventType.OrderCancelled, OrderState.CANCELLED);
        this.addTransition(OrderState.CONFIRMED, EventType.OrderCancelled, OrderState.CANCELLED);
        this.addTransition(OrderState.PAYING, EventType.OrderCancelled, OrderState.CANCELLED);
        this.addTransition(OrderState.PAID, EventType.OrderCancelled, OrderState.CANCELLED);
        this.addTransition(OrderState.SHIPPING, EventType.OrderCancelled, OrderState.CANCELLED);
        this.addTransition(OrderState.SHIPPED, EventType.OrderCancelled, OrderState.CANCELLED);
        this.addTransition(OrderState.DELIVERYING, EventType.OrderCancelled, OrderState.CANCELLED);

        // 訂單退貨
        this.addTransition(OrderState.DELIVERED, EventType.OrderReturned, OrderState.RETURNED);

        // 支付失敗
        this.addTransition(OrderState.PAYING, EventType.PaymentFailed, OrderState.FAILED);
    }
}
