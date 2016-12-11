/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.videomgr.utils;

import com.vicky.modules.messagemgr.entity.Message;
import java.util.Date;

/**
 *
 * @author Vicky
 */
public class MessageBuild {

    public static Message notPassMessage(String videoTitle, String reason) {
        Message message = new Message();
        message.setMessageTitle("视频审核通知");
        message.setContent("你的投稿" + "\"" + videoTitle + "\"审核不通过,不通过理由：" + reason);
        message.setCreateTime(new Date());
        message.setStatus(Message.UNREAD_STATSU);
        return message;
    }

    public static Message passMessage(String videoTitle) {
        Message message = new Message();
        message.setMessageTitle("视频审核通知");
        message.setContent("你的投稿" + "\"" + videoTitle + "\"审核通过了");
        message.setCreateTime(new Date());
        message.setStatus(Message.UNREAD_STATSU);
        return message;
    }

    public static Message deleteMessage(String videoTitle, String reason) {
        Message message = new Message();
        message.setMessageTitle("视频删除通知");
        message.setContent("你的投稿" + "\"" + videoTitle + "\"被管理员删除了,删除理由：" + reason);
        message.setCreateTime(new Date());
        message.setStatus(Message.UNREAD_STATSU);
        return message;
    }
}
