/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.sendemail;

/**
 * 发送邮件接口
 *
 * @author Vicky
 */
public interface SendEmailable {

    public void send(Mail mail) throws Exception;
}
