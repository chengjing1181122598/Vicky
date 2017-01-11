/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.usermgr.utils;

import com.vicky.common.utils.sendemail.Mail;

/**
 *
 * @author Administrator
 */
public class ResetPWDMail extends Mail {

    public static final String RESET_PWD_MAIL_SUBJECT = "V站账号密码重置邮件";

    public ResetPWDMail(String emailAddress, String validCode) {
        this.host = Mail.HOST;
        this.name = Mail.SENDER_NAME;
        this.receiverEmailAddress = emailAddress;
        this.senderEmailAddress = Mail.SENDER_EMAIL_ADDRESS;
        this.senderUsername = Mail.SENDER_USERNAME;
        this.senderPassword = Mail.SENDER_PASSWORD;
        this.subject = ResetPWDMail.RESET_PWD_MAIL_SUBJECT;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div style=\"font-family:'微软雅黑';\" >");
        stringBuilder.append("Hi!亲爱的" + "<b style='color:blue;'>").append(emailAddress).append("</b><br>");
        stringBuilder.append("<p>重置帐号密码.</p>");
        stringBuilder.append("<p>您的验证码是:" + "<span style='color:blue;'>").append(validCode).append("</span><p>");
        stringBuilder.append("<p>如果您没有执行过该操作,也无需担心。我们将不会在发送邮件给您。但如果您能支持一下我个人的成果，可以来V站看一看</p>");
        stringBuilder.append("<p>感谢您对V站的支持，希望您在其中获得良好的体验!</p>");
        stringBuilder.append("<p style='color:red;'>这是一封系统邮件，请勿回复!</p><br><br>");
        stringBuilder.append("</div>");
        this.message = stringBuilder.toString();
    }
}
