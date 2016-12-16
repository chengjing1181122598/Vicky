/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.update;

import com.vicky.common.utils.page.Condition;
import com.vicky.common.utils.statusmsg.StatusMsgException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 修改实体参数类,约定大约配置
 *
 * @author Vicky
 */
public class UpdateProperty {

    //更改参数前缀
    public static final String PREFIX = "update";

    private String property;
    private Object value;

    private UpdateProperty() {
    }

    public UpdateProperty(String property, Object value) {
        this.property = property;
        this.value = value;
    }

    /**
     * 解析HttpServletRequest获得要修改的属性集合
     *
     * @param request HttpServletRequest
     * @return 要修改的属性集合
     * @throws java.text.ParseException
     */
    public static List<UpdateProperty> getUpdatePropertyListFromHttpRequest(HttpServletRequest request) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        List<UpdateProperty> updatePropertys = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            if (entry.getKey().startsWith(UpdateProperty.PREFIX)) {
                String[] strings = entry.getKey().split(Condition.SPLIT_STRING);
                if (strings.length != 3) {
                    break;
                } else {
                    UpdateProperty updateProperty = new UpdateProperty();
                    updateProperty.setProperty(strings[2]);
                    switch (strings[1]) {
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
                            Date date = dateFormat.parse(entry.getValue()[0]);
                            updateProperty.setValue(date);
                            break;
                        case Condition.DOUBLE_TYPE:
                            updateProperty.setValue(new Double(entry.getValue()[0]));
                            break;
                        case Condition.INTEGER_TYPE:
                            updateProperty.setValue(new Integer(entry.getValue()[0]));
                            break;
                        case Condition.LONG_TYPE:
                            updateProperty.setValue(new Long(entry.getValue()[0]));
                            break;
                        case Condition.STRING_TYPE:
                            updateProperty.setValue(entry.getValue()[0]);
                            break;
                        default:
                            throw new StatusMsgException("不支持的数据类型：" + strings[1]);
                    }
                    if (updateProperty.getValue() != null) {
                        updatePropertys.add(updateProperty);
                    }
                }
            }
        }
        return updatePropertys;
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
}
