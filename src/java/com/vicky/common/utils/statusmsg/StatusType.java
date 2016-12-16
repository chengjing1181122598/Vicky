/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.statusmsg;

/**
 *
 * @author Administrator
 */
public enum StatusType {
    SUCCESS("success"), ERROR("error");

    private StatusType(String statusType) {
        this.statusType = statusType;
    }

    private String statusType;

    /**
     * @return the statusType
     */
    public String getStatusType() {
        return statusType;
    }

    /**
     * @param statusType the statusType to set
     */
    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }
}
