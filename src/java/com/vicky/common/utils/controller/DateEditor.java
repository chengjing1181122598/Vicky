/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义支持的日期格式转换
 *
 * @author Vicky
 */
public class DateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text);
        } catch (ParseException ex) {
            try {
                date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(text);
            } catch (ParseException ex1) {
                try {
                    date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").parse(text);
                } catch (ParseException ex2) {
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(text);
                    } catch (ParseException ex3) {
                        try {
                            date = new SimpleDateFormat("yyyy/MM/dd").parse(text);
                        } catch (ParseException ex4) {
                            try {
                                date = new SimpleDateFormat("yyyy年MM月dd日").parse(text);
                            } catch (ParseException ex5) {
                                throw new IllegalArgumentException("日期格式不对：" + text);
                            }
                        }
                    }
                }
            }
        }
        this.setValue(date);
    }

}
