/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.usermgr.utils;

import com.vicky.common.utils.sendemail.Mail;

/**
 *
 * @author Vicky
 */
public class ActivateMail extends Mail {

    public static final String ACTIVATE_MAIL_SUBJECT = "V站账号激活邮件";

    public ActivateMail(String emailAddress, String activateCode) {
        this.host = Mail.HOST;
        this.name = Mail.SENDER_NAME;
        this.receiverEmailAddress = emailAddress;
        this.senderEmailAddress = Mail.SENDER_EMAIL_ADDRESS;
        this.senderUsername = Mail.SENDER_USERNAME;
        this.senderPassword = Mail.SENDER_PASSWORD;
        this.subject = ActivateMail.ACTIVATE_MAIL_SUBJECT;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div style=\"font-family:'微软雅黑';\" >");
        stringBuilder.append("Hi!亲爱的" + "<b style='color:blue;'>").append(emailAddress).append("</b><br>");
        stringBuilder.append("<p>感谢您注册vicky.</p>");
        stringBuilder.append("<p>您的激活码是:" + "<span style='color:blue;'>").append(activateCode).append("</span><p>");
        stringBuilder.append("<p>如果您没有注册,也无需担心。我们将不会在发送邮件给您。但如果您能支持一下我个人的成果，可以来V站看一看</p>");
        stringBuilder.append("<p>感谢您对V站的支持，希望您在其中获得良好的体验!</p>");
        stringBuilder.append("<p style='color:red;'>这是一封系统邮件，请勿回复!</p><br><br>");
        stringBuilder.append("</div>");
        this.message = stringBuilder.toString();
    }

}
