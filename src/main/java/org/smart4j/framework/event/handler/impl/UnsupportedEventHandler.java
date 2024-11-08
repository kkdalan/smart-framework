package org.smart4j.framework.event.handler.impl;

import org.smart4j.framework.event.handler.EventHandler;
import org.smart4j.framework.event.model.Event;

public class UnsupportedEventHandler implements EventHandler {

    // 私有建構子，防止外部直接創建實例
    private UnsupportedEventHandler() {}

    // 靜態內部類，持有唯一的實例
    private static class SingletonHolder {
        private static final UnsupportedEventHandler INSTANCE = new UnsupportedEventHandler();
    }

    // 公共方法，提供唯一的實例
    public static UnsupportedEventHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void publishEvent(Event event) {
        System.err.println("Event handling not supported for event type: " + event.getEventType());
    }
    
    @Override
    public void consumeEvent(Event event) {
        System.err.println("Event handling not supported for event type: " + event.getEventType());
    }
}
