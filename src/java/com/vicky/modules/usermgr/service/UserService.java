/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.usermgr.service;

import com.vicky.common.utils.service.MybatisBaseService;
import com.vicky.modules.usermgr.entity.User;
import com.vicky.modules.usermgr.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Vicky
 */
@Service
public class UserService extends MybatisBaseService<User, String> {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected Mapper<User> getMapper() {
        return this.userMapper;
    }

}
