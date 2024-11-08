package org.smart4j.framework.event;

import org.smart4j.framework.event.manager.impl.CustomerEventManager;
import org.smart4j.framework.event.model.Event;
import org.smart4j.framework.event.model.EventImpl;
import org.smart4j.framework.event.model.EventType;
import org.smart4j.framework.event.state.CustomerStateMachine;

public class CustomerEventManagerTest {

	public static void main(String[] args) {

		CustomerEventManager customerManager = new CustomerEventManager(new CustomerStateMachine());

		// 模擬事件處理
		Event event = new EventImpl("CT0001", "客戶已註冊", EventType.CustomerRegistered);
		try {
			
			// 事件發送
			customerManager.publishEvent(event);
			
			// 事件消費
			customerManager.consumeEvent(event);

			// 統計資訊
			customerManager.displayEventStatistics();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
