/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.messagemgr.controller;

import com.vicky.common.controller.MyEntityController;
import com.vicky.common.utils.service.BaseService;
import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.modules.messagemgr.entity.Message;
import com.vicky.modules.messagemgr.service.MessageService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Vicky
 */
@Controller
@RequestMapping("message")
public class MessageController extends MyEntityController<Message, String> {

    @Autowired
    private MessageService messageService;

    @Override
    protected BaseService<Message, String> getBaseService() {
        return this.messageService;
    }

    @ResponseBody
    @RequestMapping("read")
    public StatusMsg read(String primaryKey) throws Exception {
        Message message = this.messageService.selectByPrimaryKey(primaryKey);
        if (!message.getUsername().equals(this.getUser().getUsername())) {
            throw new Exception("权限不够");
        }
        message.setStatus(Message.READED_STATSU);
        this.messageService.updateSelective(message);
        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put(StatusMsg.SUCCESS, "消息已改为已读状态");
        statusMsg.getMessage().put(StatusMsg.ENTITY, message);
        return statusMsg;
    }

    @Override
    @ResponseBody
    @RequestMapping("getPageData")
    public Map<String, Object> getPageData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.getPageData(request, response); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @ResponseBody
    @RequestMapping("deleteById")
    public StatusMsg deleteById(HttpServletRequest request, HttpServletResponse response, String primaryKey) throws Exception {
        Message message = this.messageService.selectByPrimaryKey(primaryKey);
        if (!message.getUsername().equals(this.getUser().getUsername())) {
            throw new Exception("权限不够");
        }
        return super.deleteById(request, response, primaryKey); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @ResponseBody
    @RequestMapping("getById")
    public StatusMsg getById(HttpServletRequest request, HttpServletResponse response, String primaryKey) {
        return super.getById(request, response, primaryKey); //To change body of generated methods, choose Tools | Templates.
    }

}
