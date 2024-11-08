package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;

public class NoOpEventHandler implements EventHandler {

    // 私有建構子，防止外部直接創建實例
    private NoOpEventHandler() {}

    // 靜態內部類，持有唯一的實例
    private static class SingletonHolder {
        private static final NoOpEventHandler INSTANCE = new NoOpEventHandler();
    }

    // 公共方法，提供唯一的實例
    public static NoOpEventHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void publishEvent(Event event) {
        System.err.println("No handler found for event type: " + event.getEventType());
    }
    
    @Override
    public void consumeEvent(Event event) {
        System.err.println("No handler found for event type: " + event.getEventType());
    }
}
