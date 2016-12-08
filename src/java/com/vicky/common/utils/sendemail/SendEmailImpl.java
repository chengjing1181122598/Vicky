/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.sendemail;

import com.vicky.common.utils.statusmsg.StatusMsgException;
import javax.mail.internet.MimeUtility;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一个简单的发送邮件类
 *
 * @author Vicky
 */
public class SendEmailImpl implements SendEmailable {

    private final Logger logger = LoggerFactory.getLogger(SendEmailImpl.class);

    @Override
    public void send(Mail mail) throws Exception {
        try {
            HtmlEmail htmlEmail = new HtmlEmail();
            htmlEmail.setHostName(mail.getHost());
            htmlEmail.setCharset(Mail.ENCODEING);
            htmlEmail.addTo(mail.getReceiverEmailAddress());
            htmlEmail.setFrom(mail.getSenderEmailAddress(), mail.getName());
            htmlEmail.setAuthentication(mail.getSenderUsername(), mail.getSenderPassword());
            htmlEmail.setSubject(mail.getSubject());
            htmlEmail.setMsg(mail.getMessage());
            for (MailAttach mailFile : mail.getMailAttachs()) {
                EmailAttachment attachment = new EmailAttachment();//创建附件  
                attachment.setPath(mailFile.getFilePath());//本地附件，绝对路径    
                //attachment.setURL(new URL("http://www.baidu.com/moumou附件"));//可添加网络上的附件  
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setName(MimeUtility.encodeText(mailFile.getFileName()));//附件名称  
                htmlEmail.attach(attachment);//添加附件到邮件,可添加多个
            }
            htmlEmail.send();
        } catch (Exception exception) {
            exception.printStackTrace();
            this.logger.error("toString：" + exception.toString());
            this.logger.error("getMessage：" + exception.getMessage());
            this.logger.error("exception：", exception);
            throw new StatusMsgException("发送邮件失败,请检查邮箱地址是否正确,如果该问题仍然存在,请联系V站官方!");
        }
    }

}
