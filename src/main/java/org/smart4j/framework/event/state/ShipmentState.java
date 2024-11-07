package org.smart4j.framework.event.state;

public enum ShipmentState implements State{
    SCHEDULED,    // 出貨已安排
    IN_TRANSIT,   // 出貨運送中
    DELIVERED,    // 已送達
    DELAYED,      // 出貨延遲
    LOST,          // 出貨丟失
}
