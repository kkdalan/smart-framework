package org.smart4j.framework.event.state;

public enum PaymentState implements State{
	PENDING,      // 付款等待中
    INITIATED,    // 付款啟動
    PROCESSING,   // 付款處理中
    COMPLETED,    // 付款完成
    FAILED,       // 付款失敗
    REFUNDED,     // 退款處理
}
