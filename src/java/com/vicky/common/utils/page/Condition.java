/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.page;

import com.vicky.common.utils.statusmsg.StatusMsgException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * 分页查询条件,约定大于配置
 *
 * @author Vicky
 */
public class Condition {

    /*
    分割参数
     */
    public static final String PREFIX = "condition";
    public static final String SPLIT_STRING = "_";

    /*
    日期类型
     */
    public static final String DEFAULT_DATE_FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT2 = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_FORMAT3 = "yyyy/MM/dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT4 = "yyyy/MM/dd";
    public static final String DEFAULT_DATE_FORMAT5 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT6 = "yyyy年MM月dd日";

    /*
    参数类型
     */
    public static final String DATE_TYPE = "D";
    public static final String STRING_TYPE = "S";
    public static final String INTEGER_TYPE = "I";
    public static final String LONG_TYPE = "L";
    public static final String DOUBLE_TYPE = "F";

    /*
    参数条件
     */
    public static final String EQUAL = "EQ";
    public static final String NOTEQUAL = "NOTEQ";
    public static final String GREATER_THAN_OR_EQUAL = "GE";
    public static final String GREATER_THAN = "GT";
    public static final String LESS_THAN_OR_EQUAL = "LE";
    public static final String LESS_THAN = "LT";
    public static final String LIKEC = "LIKEC";
    public static final String LIKER = "LIKER";
    public static final String NOTLIKEC = "NOTLIKEC";
    public static final String NOTLIKER = "NOTLIKER";
    public static final String IN = "IN";
    public static final String NOTIN = "NOTIN";
    public static final String NULL = "NULL";
    public static final String NOTNULL = "NOTNULL";

    private String condition;
    private String property;
    private Object value;
    private Set inSet;
    private Set notInSet;

    private Condition() {
        this.inSet = new HashSet();
        this.notInSet = new HashSet();
    }

    public Condition(String condition, String property, Object value, Set inSet, Set notInSet) {
        this.condition = condition;
        this.property = property;
        this.value = value;
        this.inSet = inSet;
        this.notInSet = notInSet;
    }

    /**
     * 解析HttpServletRequest获得条件集合
     *
     * @param request HttpServletRequest
     * @return 条件集合
     * @throws java.lang.Exception
     */
    public static List<Condition> getConditionListFromHttpRequest(HttpServletRequest request) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        List<Condition> conditions = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            if (entry.getKey().startsWith(Condition.PREFIX)) {
                String[] strings = entry.getKey().split(Condition.SPLIT_STRING);
                if (strings.length != 4) {
                    break;
                } else {
                    Condition condition = new Condition();
                    condition.setCondition(strings[1]);
                    condition.setProperty(strings[3]);
                    switch (strings[2]) {
                        case Condition.DATE_TYPE:
                            DateFormat dateFormat = new SimpleDateFormat();
                            if (entry.getValue()[0].contains("-")) {
                                if (entry.getValue()[0].contains(":")) {
                                    dateFormat = new SimpleDateFormat(Condition.DEFAULT_DATE_FORMAT1);
                                } else {
                                    dateFormat = new SimpleDateFormat(Condition.DEFAULT_DATE_FORMAT2);
                                }
                            }
                            if (entry.getValue()[0].contains("/")) {
                                if (entry.getValue()[0].contains(":")) {
                                    dateFormat = new SimpleDateFormat(Condition.DEFAULT_DATE_FORMAT3);
                                } else {
                                    dateFormat = new SimpleDateFormat(Condition.DEFAULT_DATE_FORMAT4);
                                }
                            }
                            if (entry.getValue()[0].contains("年")) {
                                if (entry.getValue()[0].contains(":")) {
                                    dateFormat = new SimpleDateFormat(Condition.DEFAULT_DATE_FORMAT5);
                                } else {
                                    dateFormat = new SimpleDateFormat(Condition.DEFAULT_DATE_FORMAT6);
                                }
                            }

                            if (strings[1].equals(Condition.IN)) {
                                for (String s : entry.getValue()) {
                                    Date date = dateFormat.parse(s);
                                    condition.getInSet().add(date);
                                }
                            }
                            if (strings[1].equals(Condition.NOTIN)) {
                                for (String s : entry.getValue()) {
                                    Date date = dateFormat.parse(s);
                                    condition.getNotInSet().add(date);
                                }
                            }

                            Date date = dateFormat.parse(entry.getValue()[0]);
                            condition.setValue(date);
                            break;
                        case Condition.DOUBLE_TYPE:
                            if (strings[1].equals(Condition.IN)) {
                                for (String s : entry.getValue()) {
                                    condition.getInSet().add(new Double(s));
                                }
                            }
                            if (strings[1].equals(Condition.NOTIN)) {
                                for (String s : entry.getValue()) {
                                    condition.getNotInSet().add(new Double(s));
                                }
                            }

                            condition.setValue(new Double(entry.getValue()[0]));
                            break;
                        case Condition.INTEGER_TYPE:
                            if (strings[1].equals(Condition.IN)) {
                                for (String s : entry.getValue()) {
                                    condition.getInSet().add(new Integer(s));
                                }
                            }
                            if (strings[1].equals(Condition.NOTIN)) {
                                for (String s : entry.getValue()) {
                                    condition.getNotInSet().add(new Integer(s));
                                }
                            }

                            condition.setValue(new Integer(entry.getValue()[0]));
                            break;
                        case Condition.LONG_TYPE:
                            if (strings[1].equals(Condition.IN)) {
                                for (String s : entry.getValue()) {
                                    condition.getInSet().add(new Long(s));
                                }
                            }
                            if (strings[1].equals(Condition.NOTIN)) {
                                for (String s : entry.getValue()) {
                                    condition.getNotInSet().add(new Long(s));
                                }
                            }

                            condition.setValue(new Long(entry.getValue()[0]));
                            break;
                        case Condition.STRING_TYPE:
                            if (strings[1].equals(Condition.IN)) {
                                for (String s : entry.getValue()) {
                                    condition.getInSet().add(s);
                                }
                            }
                            if (strings[1].equals(Condition.NOTIN)) {
                                for (String s : entry.getValue()) {
                                    condition.getNotInSet().add(s);
                                }
                            }

                            condition.setValue(entry.getValue()[0]);
                            break;
                        default:
                            throw new StatusMsgException("不支持的数据类型：" + strings[2]);
                    }
                    if (condition.getValue() != null || condition.getInSet().size() >= 0
                            || condition.getNotInSet().size() > 0 || strings[1].equals(Condition.NULL)
                            || strings[1].equals(Condition.NOTNULL)) {
                        conditions.add(condition);
                    }
                }
            }
        }

        return conditions;
    }

    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
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

    /**
     * @return the inSet
     */
    public Set getInSet() {
        return inSet;
    }

    /**
     * @param inSet the inSet to set
     */
    public void setInSet(Set inSet) {
        this.inSet = inSet;
    }

    /**
     * @return the notInSet
     */
    public Set getNotInSet() {
        return notInSet;
    }

    /**
     * @param notInSet the notInSet to set
     */
    public void setNotInSet(Set notInSet) {
        this.notInSet = notInSet;
    }

}
