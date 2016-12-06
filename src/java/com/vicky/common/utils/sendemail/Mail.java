/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.sendemail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件类
 *
 * @author Vicky
 */
public class Mail implements Serializable {

    public static final String ENCODEING = "UTF-8";
    public static final String HOST = "smtp.163.com";
    public static final String SENDER_EMAIL_ADDRESS = "18814127310@163.com";
    public static final String SENDER_NAME = "V站官方,请勿回复!";
    public static final String SENDER_USERNAME = "18814127310@163.com";
    public static final String SENDER_PASSWORD = "5201314vicky";

    protected String host;//服务器地址
    protected String senderEmailAddress;//发送人邮箱地址
    protected String name;//发送人昵称
    protected String receiverEmailAddress;//收件人邮箱地址
    protected String senderUsername;//发送人账号
    protected String senderPassword; //发送人密码
    protected String subject;//主题
    protected String message;//发送的内容
    protected List<MailAttach> mailAttachs = new ArrayList<>();//邮件附件文件url集合

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the senderEmailAddress
     */
    public String getSenderEmailAddress() {
        return senderEmailAddress;
    }

    /**
     * @param senderEmailAddress the senderEmailAddress to set
     */
    public void setSenderEmailAddress(String senderEmailAddress) {
        this.senderEmailAddress = senderEmailAddress;
    }

    /**
     * @return the receiverEmailAddress
     */
    public String getReceiverEmailAddress() {
        return receiverEmailAddress;
    }

    /**
     * @param receiverEmailAddress the receiverEmailAddress to set
     */
    public void setReceiverEmailAddress(String receiverEmailAddress) {
        this.receiverEmailAddress = receiverEmailAddress;
    }

    /**
     * @return the senderUsername
     */
    public String getSenderUsername() {
        return senderUsername;
    }

    /**
     * @param senderUsername the senderUsername to set
     */
    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    /**
     * @return the senderPassword
     */
    public String getSenderPassword() {
        return senderPassword;
    }

    /**
     * @param senderPassword the senderPassword to set
     */
    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

    /**
     * @return the theme
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the mailAttachs
     */
    public List<MailAttach> getMailAttachs() {
        return mailAttachs;
    }

    /**
     * @param mailAttachs the mailAttachs to set
     */
    public void setMailAttachs(List<MailAttach> mailAttachs) {
        this.mailAttachs = mailAttachs;
    }

}
