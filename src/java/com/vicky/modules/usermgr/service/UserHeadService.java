/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.usermgr.service;

import com.vicky.common.utils.service.MybatisBaseService;
import com.vicky.modules.usermgr.entity.UserHead;
import com.vicky.modules.usermgr.mapper.UserHeadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Vicky
 */
@Service
public class UserHeadService extends MybatisBaseService<UserHead, String> {

    @Autowired
    private UserHeadMapper userHeadMapper;

    @Override
    protected Mapper<UserHead> getMapper() {
        return this.userHeadMapper;
    }

}
