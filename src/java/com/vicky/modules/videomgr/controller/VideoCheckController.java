/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.videomgr.controller;

import com.vicky.common.controller.MyEntityController;
import com.vicky.common.finalpackage.Final;
import com.vicky.common.utils.DealFile.WebFileUtils;
import com.vicky.common.utils.service.BaseService;
import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.common.utils.statusmsg.StatusMsgException;
import com.vicky.modules.messagemgr.entity.Message;
import com.vicky.modules.messagemgr.service.MessageService;
import com.vicky.modules.videomgr.entity.Video;
import com.vicky.modules.videomgr.entity.VideoCheck;
import com.vicky.modules.videomgr.service.VideoCheckService;
import com.vicky.modules.videomgr.service.VideoService;
import com.vicky.modules.videomgr.utils.MessageBuild;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Vicky
 */
@Controller
@RequestMapping("videoCheck")
public class VideoCheckController extends MyEntityController<VideoCheck, String> {

    @Autowired
    private VideoCheckService checkService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private MessageService messageService;

    @Override
    protected BaseService<VideoCheck, String> getBaseService() {
        return this.checkService;
    }

    @RequestMapping("pass")
    @ResponseBody
    @Transactional
    public StatusMsg pass(String videoId) throws StatusMsgException {
        VideoCheck videoCheck = this.checkService.selectByPrimaryKey(videoId);
        Video video = new Video();
        video.setAbsolutePath(videoCheck.getAbsolutePath());
        video.setCoverAbsolutePath(videoCheck.getCoverAbsolutePath());
        video.setCoverRelativePath(videoCheck.getCoverRelativePath());
        video.setCreateTime(videoCheck.getCreateTime());
        video.setModuleId(videoCheck.getModuleId());
        video.setRelativePath(videoCheck.getRelativePath());
        video.setUsername(videoCheck.getUsername());
        video.setVideoExplain(videoCheck.getVideoExplain());
        video.setVideoId(videoCheck.getVideoId());
        video.setVideoName(videoCheck.getVideoName());
        video.setVideoTitle(videoCheck.getVideoTitle());
        this.videoService.save(video);
        this.checkService.deleteById(videoId);
        WebFileUtils.deleteFile(video.getAbsolutePath());
        WebFileUtils.deleteFile(video.getCoverAbsolutePath());

        Message message = MessageBuild.passMessage(video.getVideoTitle());
        message.setUsername(video.getUsername());
        this.messageService.save(message);

        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put(StatusMsg.MESSAGE, "视频审核已通过");
        statusMsg.getMessage().put(StatusMsg.ENTITY, video);
        return statusMsg;
    }

    @RequestMapping("notPass")
    @ResponseBody
    public StatusMsg notPass(String videoId, String reason) throws StatusMsgException {

        VideoCheck videoCheck = this.checkService.selectByPrimaryKey(videoId);
        this.checkService.deleteById(videoId);
        WebFileUtils.deleteFile(videoCheck.getAbsolutePath());
        WebFileUtils.deleteFile(videoCheck.getCoverAbsolutePath());

        Message message = MessageBuild.notPassMessage(videoCheck.getVideoTitle(), reason);
        message.setUsername(videoCheck.getUsername());
        this.messageService.save(message);

        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put(StatusMsg.MESSAGE, "视频审核不通过");
        statusMsg.getMessage().put(StatusMsg.ENTITY, videoCheck);
        return statusMsg;
    }

    @Override
    @RequestMapping("getById")
    @ResponseBody
    public StatusMsg getById(HttpServletRequest request, HttpServletResponse response, String primaryKey) {
        return super.getById(request, response, primaryKey); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RequestMapping("getPageData")
    @ResponseBody
    public Map<String, Object> getPageData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.getPageData(request, response); //To change body of generated methods, choose Tools | Templates.
    }

    @RequestMapping("save")
    @ResponseBody
    public StatusMsg save(@RequestParam("videoCover") MultipartFile videoCover,
            @RequestParam("videoFile") MultipartFile videoFile, @Valid VideoCheck t, BindingResult result) throws Exception {
        videoCover.getInputStream().available();
        videoFile.getInputStream().available();

        if (result.hasErrors()) {
            throw new StatusMsgException(result.getFieldError().getDefaultMessage());
        }

        String lowerCase1 = videoCover.getOriginalFilename().toLowerCase();
        if (!lowerCase1.endsWith(".jpg") || !lowerCase1.endsWith(".png") || !lowerCase1.endsWith(".gif") || !lowerCase1.endsWith(".jpeg")) {
            throw new StatusMsgException("封面必须为图片文件");
        }

        String lowerCase2 = videoFile.getOriginalFilename().toLowerCase();
        if (!lowerCase2.endsWith(".mp4")) {
            throw new StatusMsgException("视频只能为mp4文件");
        }

        if (videoCover.getSize() > Video.COVER_SIZE) {
            throw new StatusMsgException("封面文件不能大于" + Video.COVER_SIZE / Final.FILE_SIZE_M + "M");
        }

        if (videoFile.getSize() > Video.VIDEO_SIZE) {
            throw new StatusMsgException("视频文件不能大于" + Video.VIDEO_SIZE / Final.FILE_SIZE_M + "M");
        }

        if (t.getModuleId() == null || t.getModuleId().equals("")) {
            throw new StatusMsgException("视频模块id不能为空");
        }
        if (t.getVideoTitle() == null || t.getVideoTitle().equals("")) {
            throw new StatusMsgException("视频标题不能为空");
        }

        String[] strings1 = WebFileUtils.savePublicFileAtOtherServer(videoCover,
                Final.SERVER_PATH, Final.WEB_ROOT_PATH, Final.IMAGE_PATH, this.getUser().getUsername());
        String[] strings2 = WebFileUtils.savePublicFileAtOtherServer(videoFile,
                Final.SERVER_PATH, Final.WEB_ROOT_PATH, Final.VEDIO_PATH, this.getUser().getUsername());
        t.setAbsolutePath(strings2[0]);
        t.setRelativePath(strings2[1]);
        t.setCoverAbsolutePath(strings1[0]);
        t.setCoverRelativePath(strings1[1]);
        t.setCreateTime(new Date());
        t.setUsername(this.getUser().getUsername());
        t.setVideoName(videoFile.getOriginalFilename());
        this.checkService.save(t);
        t.setAbsolutePath(null);
        t.setRelativePath(null);
        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put(StatusMsg.MESSAGE, "视频上传成功,正在等待管理员的审核,请留意消息通知!");
        statusMsg.getMessage().put(StatusMsg.ENTITY, t);
        return statusMsg;
    }

}
