/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.controller;

import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.common.utils.statusmsg.StatusMsgException;
import com.vicky.common.utils.statusmsg.StatusType;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

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
        if (!exception.getClass().equals(StatusMsgException.class)) {
            exception.printStackTrace();
            this.getLogger().error("toString：" + exception.toString());
            this.getLogger().error("getMessage：" + exception.getMessage());
            this.getLogger().error("exception：", exception);
        }
        StatusMsg statusMsg = new StatusMsg(StatusType.ERROR);
        statusMsg.getMessage().put("toString", exception.toString());
        statusMsg.getMessage().put(StatusMsg.MESSAGE, exception.getMessage());
        return statusMsg;
    }

    protected StatusMsg simpleBuildErrorMsg(String message) {
        StatusMsg statusMsg = new StatusMsg(StatusType.ERROR);
        statusMsg.getMessage().put(StatusMsg.MESSAGE, message);
        return statusMsg;
    }

    protected StatusMsg simpleBuildErrorMsg(String message, Object entity) {
        StatusMsg statusMsg = new StatusMsg(StatusType.ERROR);
        statusMsg.getMessage().put(StatusMsg.MESSAGE, message);
        statusMsg.getMessage().put(StatusMsg.ENTITY, entity);
        return statusMsg;
    }

    protected StatusMsg simpleBuildSuccessMsg(String message) {
        StatusMsg statusMsg = new StatusMsg(StatusType.SUCCESS);
        statusMsg.getMessage().put(StatusMsg.MESSAGE, message);
        return statusMsg;
    }

    protected StatusMsg simpleBuildSuccessMsg(String message, Object entity) {
        StatusMsg statusMsg = new StatusMsg(StatusType.SUCCESS);
        statusMsg.getMessage().put(StatusMsg.MESSAGE, message);
        statusMsg.getMessage().put(StatusMsg.ENTITY, entity);
        return statusMsg;
    }

    protected StatusMsg simpleBuildSuccessMsg(Object entity) {
        StatusMsg statusMsg = new StatusMsg(StatusType.SUCCESS);
        statusMsg.getMessage().put(StatusMsg.ENTITY, entity);
        return statusMsg;
    }
}
