/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.messagemgr.service;

import com.vicky.common.utils.service.MybatisBaseService;
import com.vicky.modules.messagemgr.entity.Message;
import com.vicky.modules.messagemgr.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Vicky
 */
@Service
public class MessageService extends MybatisBaseService<Message, String> {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    protected Mapper<Message> getMapper() {
        return this.messageMapper;
    }

}
