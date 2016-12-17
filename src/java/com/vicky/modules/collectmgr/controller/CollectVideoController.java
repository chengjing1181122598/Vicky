/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.collectmgr.controller;

import com.vicky.common.controller.MyEntityController;
import com.vicky.common.utils.service.BaseService;
import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.common.utils.statusmsg.StatusMsgException;
import com.vicky.modules.collectmgr.entity.CollectVideo;
import com.vicky.modules.collectmgr.service.CollectVideoService;
import com.vicky.modules.usermgr.entity.User;
import com.vicky.modules.videomgr.entity.Video;
import com.vicky.modules.videomgr.service.VideoService;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("collectVideo")
public class CollectVideoController extends MyEntityController<CollectVideo, String> {

    @Autowired
    private CollectVideoService collectVideoService;
    @Autowired
    private VideoService videoService;

    @Override
    protected BaseService<CollectVideo, String> getBaseService() {
        return collectVideoService;
    }

    @Override
    @RequestMapping("deleteById")
    @ResponseBody
    public StatusMsg deleteById(HttpServletRequest request, HttpServletResponse response, String primaryKey) throws Exception {
        CollectVideo collectVideo = this.collectVideoService.selectByPrimaryKey(primaryKey);
        if (!collectVideo.getUsername().equals(super.getUser().getUsername())) {
            throw new StatusMsgException("权限不够");
        }
        return super.deleteById(request, response, primaryKey); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RequestMapping("save")
    @ResponseBody
    public StatusMsg save(HttpServletRequest request, HttpServletResponse response, CollectVideo t) throws Exception {
        User user = super.getUser();
        Video video = this.videoService.selectByPrimaryKey(t.getVideoId());
        t.setUsername(user.getUsername());
        t.setCoverRelativePath(video.getCoverRelativePath());
        t.setCreateTime(new Date());
        t.setVideoTitle(video.getVideoTitle());

        return super.save(request, response, t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RequestMapping("getPageData")
    @ResponseBody
    public Map<String, Object> getPageData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.getPageData(request, response); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
