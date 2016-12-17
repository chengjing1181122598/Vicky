/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.associatemapper;

import java.lang.reflect.Field;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 *
 * @author Vicky
 */
public class AnalysisAnnotation {

    public static <T> String getTableName(Class<T> entity) {
        Table table = entity.getAnnotation(Table.class);
        return table.name();
    }

    public static <T> String getPropertyName(Class<T> entity, String property) throws NoSuchFieldException {
        Field field = entity.getDeclaredField(property);
        Column column = field.getAnnotation(Column.class);
        return column.name();
    }
}
