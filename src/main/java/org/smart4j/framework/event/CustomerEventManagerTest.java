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
		// 客戶相關事件
		try {

			Event event = new EventImpl("CUST001", "客戶註冊", EventType.CustomerRegistered);
			customerManager.publishEvent(event);
			customerManager.consumeEvent(event);
			
			event = new EventImpl("CUST002", "客戶更新", EventType.CustomerUpdated);
			customerManager.publishEvent(event);
			customerManager.consumeEvent(event);
			
			event = new EventImpl("CUST003", "客戶刪除", EventType.CustomerDeleted);
			customerManager.publishEvent(event);
			customerManager.consumeEvent(event);

			// 統計資訊
			customerManager.displayEventStatistics();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
