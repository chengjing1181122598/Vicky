/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.collectmgr.service;

import com.vicky.common.utils.service.MybatisBaseService;
import com.vicky.modules.collectmgr.entity.VideoLike;
import com.vicky.modules.collectmgr.mapper.VideoLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Administrator
 */
@Service
public class VideoLikeService extends MybatisBaseService<VideoLike, String> {

    @Autowired
    private VideoLikeMapper videoLikeMapper;

    @Override
    protected Mapper<VideoLike> getMapper() {
        return this.videoLikeMapper;
    }

}
