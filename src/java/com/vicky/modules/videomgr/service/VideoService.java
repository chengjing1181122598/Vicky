/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.videomgr.service;

import com.vicky.common.service.MemcachedService;
import com.vicky.common.utils.service.MybatisBaseService;
import com.vicky.modules.videomgr.entity.Video;
import com.vicky.modules.videomgr.mapper.VideoMapper;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private MemcachedService memcachedService;

    @Override
    public void deleteById(String id) {
        this.memcachedService.deleteMemcached(Video.MEMCACHED_PREFFIX + id);
        super.deleteById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Video t) {
        super.save(t); //To change body of generated methods, choose Tools | Templates.
        this.memcachedService.setMemcached(Video.MEMCACHED_PREFFIX + t.getVideoId(), Video.MEMCACHED_TIME_SECOND, t);
    }

    @Override
    public Video selectByPrimaryKey(String id) {
        Video video = this.memcachedService.getMemcached(Video.MEMCACHED_PREFFIX + id);
        if (video == null) {
            video = super.selectByPrimaryKey(id);
            if (video != null) {
                this.memcachedService.setMemcached(Video.MEMCACHED_PREFFIX + video.getVideoId(), Video.MEMCACHED_TIME_SECOND, video);
            }
            return video;
        } else {
            return video;
        }
    }

    @Override
    protected Mapper<Video> getMapper() {
        return this.videoMapper;
    }

    public List<Map> getPerSize() {
        return this.videoMapper.getPerSize();
    }

    public List<Map> getSlipeData() {
        return this.videoMapper.getSlipeData();
    }

}
