/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.collectmgr.service;

import com.vicky.common.utils.service.MybatisBaseService;
import com.vicky.modules.collectmgr.entity.CollectUser;
import com.vicky.modules.collectmgr.mapper.CollectUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Administrator
 */
@Service
public class CollectUserService extends MybatisBaseService<CollectUser, String> {

    @Autowired
    private CollectUserMapper collectUserMapper;

    @Override
    protected Mapper<CollectUser> getMapper() {
        return this.collectUserMapper;
    }

}
