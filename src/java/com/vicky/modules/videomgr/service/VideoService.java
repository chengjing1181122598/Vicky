/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.videomgr.service;

import com.vicky.common.utils.service.MybatisBaseService;
import com.vicky.modules.videomgr.entity.Video;
import com.vicky.modules.videomgr.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Vicky
 */
@Service
public class VideoService extends MybatisBaseService<Video, String> {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    protected Mapper<Video> getMapper() {
        return this.videoMapper;
    }

}
