package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;

public class SimpleEventHandler implements EventHandler {

	@Override
	public void publishEvent(Event event) {
		System.out.println("發送事件: " + event.getEventId());
	}
	
	@Override
	public void consumeEvent(Event event) throws Exception {

		// 在這裡，你可以執行事件的具體處理邏輯
		System.out.println("處理事件ID: " + event.getEventId());
		System.out.println("事件數據: " + event.getEventData());

		// 例如，假設事件是成功處理的，這裡可以加入一些處理邏輯
		if (event.getEventData().toString().contains("失敗")) {
			// 模擬處理失敗的情況
			throw new Exception("事件處理失敗: " + event.getEventId());
		}

		// 成功處理事件的情況
		System.out.println("事件處理成功");
	}

}
