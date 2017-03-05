/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.rankmgr.service;

import com.vicky.common.finalpackage.Final;
import com.vicky.common.service.MemcachedService;
import com.vicky.common.utils.service.MybatisBaseService;
import com.vicky.modules.rankmgr.entity.VideoPlayNumber;
import com.vicky.modules.rankmgr.mapper.PlayNumberMapper;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Vicky
 */
@Service
@Transactional
public class PlayNumberService extends MybatisBaseService<VideoPlayNumber, String> {

    @Autowired
    private PlayNumberMapper playNumberMapper;
    @Autowired
    private MemcachedService memcachedService;

    @Override
    protected Mapper<VideoPlayNumber> getMapper() {
        return this.playNumberMapper;
    }

    public VideoPlayNumber increment(HttpServletRequest request, String videoId) {

        VideoPlayNumber v = new VideoPlayNumber();
        v.setVideoId(videoId);

        String realIpAddr = Final.getIpAddr(request);
        if (this.memcachedService.getMemcached(VideoPlayNumber.MEMCACHED_IP_PREFFIX + realIpAddr + videoId) != null) {
            return this.playNumberMapper.selectOne(v);
        }
        VideoPlayNumber videoPlayNumber;
        boolean loop = false;
        do {
            videoPlayNumber = this.playNumberMapper.selectOne(v);
            videoPlayNumber.setNumber(videoPlayNumber.getNumber() + 1);
            if (this.playNumberMapper.increment(videoPlayNumber) == 0) {
                loop = true;
            }
        } while (loop);
        byte b = 0;
        this.memcachedService.setMemcached(VideoPlayNumber.MEMCACHED_IP_PREFFIX + realIpAddr + videoId, VideoPlayNumber.MEMCACHED_IP_TIMEOUT, b);
        return videoPlayNumber;
    }

}
