package org.smart4j.framework.event.state;

public enum OrderState implements State{
	NEW,          // 新訂單
    CREATED,      // 訂單已創建
    CONFIRMED,    // 訂單已確認
    PAYING,       // 支付進行中
    PAID,         // 支付完成
    SHIPPING,     // 出貨安排中
    SHIPPED,      // 訂單已出貨
    DELIVERYING,  // 配送中
    DELIVERED,    // 已送達
    FAILED,       // 配送失敗
    CANCELLED,    // 訂單取消
    RETURNED,     // 訂單退貨
}
