/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.statusmsg;

import java.util.HashMap;
import java.util.Map;

/**
 * Ajax响应消息类
 *
 * @author Vicky
 */
public class StatusMsg {

    public static final String MESSAGE = "message";
    public static final String ENTITY = "entity";

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String status;
    private Map<String, Object> message = new HashMap<>();

    public StatusMsg(StatusType statusType) {
        this.status = statusType.getStatusType();
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public Map<String, Object> getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }
}
