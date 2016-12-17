/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.associatemapper;

/**
 *
 * @author Vicky
 */
public class Order {

    private Class entity;
    private String property;
    private OrderType orderType;

    public Order(Class entity, String property, OrderType orderType) {
        this.entity = entity;
        this.property = property;
        this.orderType = orderType;
    }
    
    public enum OrderType {
    ASC("asc"), DESC("desc");

    private OrderType(String type) {
        this.type = type;
    }
    private String type;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}

    /**
     * @return the entity
     */
    public Class getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(Class entity) {
        this.entity = entity;
    }

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * @return the orderType
     */
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * @param orderType the orderType to set
     */
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

}
