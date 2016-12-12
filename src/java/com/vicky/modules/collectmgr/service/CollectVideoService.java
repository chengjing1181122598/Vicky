/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.collectmgr.service;

import com.vicky.common.utils.service.MybatisBaseService;
import com.vicky.modules.collectmgr.entity.CollectVideo;
import com.vicky.modules.collectmgr.mapper.CollectVideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Administrator
 */
@Service
public class CollectVideoService extends MybatisBaseService<CollectVideo, String> {

    @Autowired
    private CollectVideoMapper collectVideoMapper;

    @Override
    protected Mapper<CollectVideo> getMapper() {
        return this.collectVideoMapper;
    }

}
