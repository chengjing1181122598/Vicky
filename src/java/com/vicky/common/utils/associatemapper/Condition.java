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
public class Condition {

    private Class entity;
    private String property;
    private ConditionType conditionType;
    private Object value;

    public Condition(Class entity, String property, ConditionType conditionType, Object value) {
        this.entity = entity;
        this.property = property;
        this.conditionType = conditionType;
        this.value = value;
    }

    public static class TableCondition {

        private Class entity;
        private String property;

        public TableCondition(Class entity, String property) {
            this.entity = entity;
            this.property = property;
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
    }

    public enum ConditionType {
        EQUAL(" = "), NOTEQUAL(" != "), GREATER_THAN_OR_EQUAL(" >= "), GREATER_THAN(" > "), LESS_THAN_OR_EQUAL(" <= "),
        LESS_THAN(" < "), LIKEC(" like "), NULL(" is null "), NOTNULL(" is not null");

        private String type;

        private ConditionType(String type) {
            this.type = type;
        }

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
     * @return the conditionType
     */
    public ConditionType getConditionType() {
        return conditionType;
    }

    /**
     * @param conditionType the conditionType to set
     */
    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

}
