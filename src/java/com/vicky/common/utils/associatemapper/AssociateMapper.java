/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.associatemapper;

import com.vicky.common.utils.associatemapper.Condition.TableCondition;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.RowBounds;

/**
 *
 * @author Vicky
 */
public interface AssociateMapper {

    @SelectProvider(type = SqlBuilder.class, method = "list")
    public List<Map<String, Object>> list(Criteria criteria, RowBounds rowBounds);

    @SelectProvider(type = SqlBuilder.class, method = "listCount")
    public int listCount(Criteria criteria);

    public class Criteria {

        private final List<Table> tables = new ArrayList<>();
        private final List<Condition> conditions = new ArrayList<>();
        private final List<Order> orders = new ArrayList<>();

        private Table table;

        public void selectTable(Class entity) {
            this.table = new Table(entity);
            this.tables.add(table);
        }

        public void selectProperty(String property, String alias) {
            this.table.addProperty(property, alias);
        }

        public void selectCondition(Class entity, String property, Condition.ConditionType type, Object value) {
            Condition condition = new Condition(entity, property, type, value);
            this.conditions.add(condition);
        }

        public void selectOrder(Class entity, String property, Order.OrderType type) {
            Order order = new Order(entity, property, type);
            this.orders.add(order);
        }

        /**
         * @return the tables
         */
        public List<Table> getTables() {
            return tables;
        }

        /**
         * @return the conditions
         */
        public List<Condition> getConditions() {
            return conditions;
        }

        /**
         * @return the orders
         */
        public List<Order> getOrders() {
            return orders;
        }
    }

    public class SqlBuilder {

        public String list(Criteria criteria) throws NoSuchFieldException {
            String sql = new SQL() {
                {
                    List<Table> tables = criteria.getTables();
                    List<Condition> conditions = criteria.getConditions();
                    List<Order> orders = criteria.getOrders();
                    Set<String> tableNames = new HashSet<>();
                    for (Table table : tables) {
                        String tableName = AnalysisAnnotation.getTableName(table.getEntity());
                        tableNames.add(tableName);
                        for (Table.Property property : table.getPropertys()) {
                            String propertyName = AnalysisAnnotation.getPropertyName(table.getEntity(), property.getProperty());
                            SELECT(tableName + "." + propertyName + " " + property.getAlias());
                        }
                    }
                    for (String tableName : tableNames) {
                        FROM(tableName);
                    }
                    for (Condition condition : conditions) {
                        String conditionType = condition.getConditionType().getType();
                        String preffix = AnalysisAnnotation.getTableName(condition.getEntity()) + "."
                                + AnalysisAnnotation.getPropertyName(condition.getEntity(), condition.getProperty())
                                + conditionType;
                        String property = null;
                        if (Date.class.isInstance(condition.getValue())) {
                            Date date = (Date) condition.getValue();
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
                            property = "'" + dateFormat.format(date) + "'";
                        }
                        if (Integer.class.isInstance(condition.getValue()) || Double.class.isInstance(condition.getValue())
                                || Long.class.isInstance(condition.getValue())) {
                            property = "" + condition.getValue() + "";
                        }
                        if (String.class.isInstance(condition.getValue())) {
                            property = "'" + condition.getValue() + "'";
                        }
                        if (TableCondition.class.isInstance(condition.getValue())) {
                            TableCondition tableCondition = (TableCondition) condition.getValue();
                            property = AnalysisAnnotation.getTableName(tableCondition.getEntity()) + "." + tableCondition.getProperty();
                        }
                        switch (condition.getConditionType()) {
                            case NULL:
                                WHERE(preffix);
                                break;
                            case NOTNULL:
                                WHERE(preffix);
                                break;
                            default:
                                WHERE(preffix + property);
                        }
                    }
                    for (Order order : orders) {
                        ORDER_BY(AnalysisAnnotation.getTableName(order.getEntity())
                                + "." + AnalysisAnnotation.getPropertyName(order.getEntity(), order.getProperty())
                                + " " + order.getOrderType().getType());
                    }

                }
            }.toString();
            System.out.println(sql);
            return sql;
        }

        public String listCount(Criteria criteria) throws NoSuchFieldException {
            String sql = new SQL() {
                {
                    List<Table> tables = criteria.getTables();
                    List<Condition> conditions = criteria.getConditions();
                    Set<String> tableNames = new HashSet<>();
                    SELECT("count(*)");
                    for (Table table : tables) {
                        FROM(AnalysisAnnotation.getTableName(table.getEntity()));
                    }
                    for (Condition condition : conditions) {
                        String conditionType = condition.getConditionType().getType();
                        String preffix = AnalysisAnnotation.getTableName(condition.getEntity()) + "."
                                + AnalysisAnnotation.getPropertyName(condition.getEntity(), condition.getProperty())
                                + conditionType;
                        String property = null;
                        if (Date.class.isInstance(condition.getValue())) {
                            Date date = (Date) condition.getValue();
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
                            property = "'" + dateFormat.format(date) + "'";
                        }
                        if (Integer.class.isInstance(condition.getValue()) || Double.class.isInstance(condition.getValue())
                                || Long.class.isInstance(condition.getValue())) {
                            property = "" + condition.getValue() + "";
                        }
                        if (String.class.isInstance(condition.getValue())) {
                            property = "'" + condition.getValue() + "'";
                        }
                        if (TableCondition.class.isInstance(condition.getValue())) {
                            TableCondition tableCondition = (TableCondition) condition.getValue();
                            property = AnalysisAnnotation.getTableName(tableCondition.getEntity()) + "." + tableCondition.getProperty();
                        }
                        switch (condition.getConditionType()) {
                            case NULL:
                                WHERE(preffix);
                                break;
                            case NOTNULL:
                                WHERE(preffix);
                                break;
                            default:
                                WHERE(preffix + property);
                        }
                    }
                }
            }.toString();
            System.out.println(sql);
            return sql;
        }
    }

}
