package org.smart4j.framework.event.model;

public enum EventType {

	// 客戶相關事件
	CustomerRegistered("客戶註冊"),
	CustomerUpdated("客戶更新"),
	CustomerDeleted("客戶刪除"),

	// 訂單相關事件
	OrderCreated("訂單創建"),
	OrderConfirmed("訂單確認"),
	OrderShipped("訂單出貨"),
	OrderDelivered("訂單送達"),
	OrderCancelled("訂單取消"),
	OrderReturned("訂單退貨"),

	// 付款相關事件
	PaymentReady("付款啟動"),
	PaymentCompleted("付款完成"),
	PaymentFailed("付款失敗"),
	PaymentRefunded("付款退款"),

	// 出貨相關事件
	ShipmentScheduled("出貨安排"),
	ShipmentInTransit("出貨中"),
	ShipmentDelivered("出貨已送達"),
	ShipmentDelayed("出貨延遲"),
	ShipmentLost("出貨丟失");

	private final String text;

	EventType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public static EventType fromString(String text) {
		for (EventType type : EventType.values()) {
			if (type.name().equalsIgnoreCase(text)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unexpected value: " + text);
	}
}
