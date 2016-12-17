/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.associatemapper;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vicky
 */
public class Table {

    private Class entity;
    private Set<Property> propertys = new HashSet<>();

    public Table(Class entity) {
        this.entity = entity;
    }

    public void addProperty(String property, String alias) {
        this.propertys.add(new Property(property, alias));
    }

    public class Property {

        private String property;
        private String alias;

        public Property(String property, String alias) {
            this.property = property;
            this.alias = alias;
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
         * @return the alias
         */
        public String getAlias() {
            return alias;
        }

        /**
         * @param alias the alias to set
         */
        public void setAlias(String alias) {
            this.alias = alias;
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
     * @return the propertys
     */
    public Set<Property> getPropertys() {
        return propertys;
    }

    /**
     * @param propertys the propertys to set
     */
    public void setPropertys(Set<Property> propertys) {
        this.propertys = propertys;
    }
}
