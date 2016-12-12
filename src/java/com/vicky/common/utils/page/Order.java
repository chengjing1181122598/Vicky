/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.page;

import com.vicky.common.utils.statusmsg.StatusMsgException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * 分页查询排序,约定大于配置
 *
 * @author Vicky
 */
public class Order {

    /*
    排序字段,类型参数
     */
    public static final String ORDER_PROPERTY = "order_property";
    public static final String ORDER_TYPE = "order_type";

    /*
    排序类型
     */
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    public Order(String property, String type) {
        this.property = property;
        this.type = type;
    }

    private Order() {

    }

    /**
     * 解析HttpServletRequest获得条件集合
     *
     * @param request HttpServletRequest
     * @return 排序集合
     * @throws java.lang.Exception
     */
    public static List<Order> getOrderListFromHttpRequest(HttpServletRequest request) throws Exception {
        List<Order> orders = new ArrayList<>();
        String[] propertys = request.getParameterValues(Order.ORDER_PROPERTY);
        if (propertys == null) {
            return orders;
        }
        String[] types = request.getParameterValues(Order.ORDER_TYPE);
        for (int i = 0; i < propertys.length; i++) {
            System.out.println(types[i].equals(Order.ASC));
            System.out.println(types[i].equals(Order.DESC));
            if (!types[i].equals(Order.ASC) && !types[i].equals(Order.DESC)) {
                throw new StatusMsgException("排序只能是asc或者desc,当前排序为：" + types[i]);
            }
            Order order = new Order();
            order.setProperty(propertys[i]);
            if (types == null || types.length - 1 < i) {
                order.setType(Order.ASC);
            } else {
                order.setType(types[i]);
            }
            orders.add(order);
        }
        return orders;
    }

    private String property;
    private String type;

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
