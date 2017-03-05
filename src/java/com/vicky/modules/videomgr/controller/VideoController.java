/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.videomgr.controller;

import com.vicky.common.controller.MyEntityController;
import com.vicky.common.utils.DealFile.WebFileUtils;
import com.vicky.common.utils.page.Page;
import com.vicky.common.utils.service.BaseService;
import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.common.utils.statusmsg.StatusType;
import com.vicky.modules.collectmgr.entity.CollectVideo;
import com.vicky.modules.collectmgr.service.CollectVideoService;
import com.vicky.modules.commentmgr.entity.CommentFloor;
import com.vicky.modules.commentmgr.service.CommentFloorService;
import com.vicky.modules.messagemgr.entity.Message;
import com.vicky.modules.messagemgr.service.MessageService;
import com.vicky.modules.rankmgr.entity.VideoPlayNumber;
import com.vicky.modules.rankmgr.service.PlayNumberService;
import com.vicky.modules.videomgr.entity.Video;
import com.vicky.modules.videomgr.service.VideoService;
import com.vicky.modules.videomgr.utils.MessageBuild;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Vicky
 */
@Controller
@RequestMapping("video")
public class VideoController extends MyEntityController<Video, String> {
    
    @Autowired
    private VideoService videoService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private CollectVideoService collectVideoService;
    @Autowired
    private CommentFloorService commentFloorService;
    @Autowired
    private PlayNumberService numberService;
    
    @Override
    protected BaseService<Video, String> getBaseService() {
        return this.videoService;
    }
    
    @ResponseBody
    @RequestMapping("getSlipeData")
    public Map<String, Object> getSlipeData() {
        Map<String, Object> map = new HashMap<>();
        List<Map> list = this.videoService.getSlipeData();
        map.put(Page.TOTAL_KEY, list.size());
        map.put(Page.DATA_KEY, list);
        return map;
    }
    
    @ResponseBody
    @RequestMapping("getPerSize")
    public StatusMsg getPerSize() {
        StatusMsg statusMsg = new StatusMsg(StatusType.SUCCESS);
        statusMsg.getMessage().put(StatusMsg.ENTITY, this.videoService.getPerSize());
        return statusMsg;
    }
    
    @Override
    @ResponseBody
    @RequestMapping("getPageData")
    public Map<String, Object> getPageData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.getPageData(request, response); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @ResponseBody
    @RequestMapping("deleteById")
    @Transactional
    public StatusMsg deleteById(HttpServletRequest request, HttpServletResponse response, String primaryKey) throws Exception {
        Video video = this.videoService.selectByPrimaryKey(primaryKey);
        if (this.getManager() != null) {
            String reason = request.getParameter("reason");
            Message message = MessageBuild.deleteMessage(video.getVideoTitle(), reason);
            message.setUsername(video.getUsername());
            this.messageService.save(message);
        } else if (!video.getUsername().equals(this.getUser().getUsername())) {
            throw new Exception("权限不够");
        }
        WebFileUtils.deleteFile(video.getAbsolutePath());
        WebFileUtils.deleteFile(video.getCoverAbsolutePath());
        
        this.collectVideoService.delete(new CollectVideo(primaryKey));
        this.commentFloorService.delete(new CommentFloor(primaryKey));
        this.numberService.delete(new VideoPlayNumber(primaryKey));
        
        return super.deleteById(request, response, primaryKey); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @ResponseBody
    @RequestMapping("getById")
    public StatusMsg getById(HttpServletRequest request, HttpServletResponse response, String primaryKey) {
        return super.getById(request, response, primaryKey); //To change body of generated methods, choose Tools | Templates.
    }
    
}
