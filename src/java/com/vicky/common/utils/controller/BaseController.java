/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.controller;

import com.vicky.common.utils.statusmsg.StatusMsg;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基本的controller,包括了异常统一处理,日期时间数据绑定转换,获取日志记录Logger
 *
 * @author Vicky
 */
public class BaseController {

    /**
     * 获取日志Logger,为org.slf4j.Logger
     *
     * @return org.slf4j.Logger
     */
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    /**
     * 日期时间数据绑定转换
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
        binder.registerCustomEditor(Time.class, new CustomDateEditor(new SimpleDateFormat("HH:mm:ss"), false));
    }

    /**
     * 统一异常处理,如果出现异常,StatusMsg返回json message将包含"toString","getMessage"两个键
     * <br>
     * 值分别为Exception.toString()和值分别为Exception.getMessage()
     *
     * @param request HttpServletRequest
     * @param exception Exception
     * @return StatusMsg Ajax响应消息类
     */
    @ExceptionHandler
    @ResponseBody
    protected StatusMsg exceptionHandler(HttpServletRequest request, Exception exception) {
        this.getLogger().error("toString：" + exception.toString());
        this.getLogger().error("getMessage：" + exception.getMessage());
        this.getLogger().error("exception：", exception);
        StatusMsg statusMsg = new StatusMsg();
        statusMsg.setStatus(StatusMsg.ERROR);
        statusMsg.getMessage().put("toString", exception.toString());
        statusMsg.getMessage().put("getMessage", exception.getMessage());
        exception.printStackTrace();
        return statusMsg;
    }
}
