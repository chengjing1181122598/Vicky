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
import com.vicky.modules.messagemgr.entity.Message;
import com.vicky.modules.messagemgr.service.MessageService;
import com.vicky.modules.videomgr.entity.Video;
import com.vicky.modules.videomgr.entity.VideoCheck;
import com.vicky.modules.videomgr.service.VideoCheckService;
import com.vicky.modules.videomgr.service.VideoService;
import com.vicky.modules.videomgr.utils.Manager;
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

    @RequestMapping("login")
    @ResponseBody
    public StatusMsg login(Manager manager) {
        if (manager.getUsername().equals(Manager.DEFAULT_MANAGER_USERNAME)
                && manager.getPassword().equals(Manager.DEFAULT_MANAGER_PASSWORD)) {
            super.request.getSession().setAttribute("manager", manager);
            return super.simpleBuildSuccessMsg("管理员登录成功");
        } else {
            return super.simpleBuildErrorMsg("管理员账号或者密码错误");
        }
    }

    @RequestMapping("pass")
    @ResponseBody
    @Transactional
    public StatusMsg pass(String videoId) {
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

        Message message = MessageBuild.passMessage(video.getVideoTitle());
        message.setUsername(video.getUsername());
        this.messageService.save(message);

        return super.simpleBuildSuccessMsg("视频审核已通过", video);
    }

    public StatusMsg notPass(String videoId, String reason) {
        if (reason == null || reason.length() == 0) {
            return super.simpleBuildErrorMsg("理由不能为空");
        }
        VideoCheck videoCheck = this.checkService.selectByPrimaryKey(videoId);
        this.checkService.deleteById(videoId);
        WebFileUtils.deleteFile(videoCheck.getAbsolutePath());
        WebFileUtils.deleteFile(videoCheck.getCoverAbsolutePath());

        Message message = MessageBuild.notPassMessage(videoCheck.getVideoTitle(), reason);
        message.setUsername(videoCheck.getUsername());
        this.messageService.save(message);

        return super.simpleBuildSuccessMsg("视频审核不通过", videoCheck);
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
        return  super.getPageData(request, response);
    }

    @RequestMapping("save")
    @ResponseBody
    public StatusMsg save(@RequestParam("files") MultipartFile[] files, @Valid VideoCheck t, BindingResult result) throws Exception {
        MultipartFile videoCover = files[0];
        MultipartFile videoFile = files[1];
        videoCover.getInputStream().available();
        videoFile.getInputStream().available();

        if (result.hasErrors()) {
            return super.simpleBuildErrorMsg(result.getFieldError().getDefaultMessage());
        }

        String lowerCase1 = videoCover.getOriginalFilename().toLowerCase();
        if (!lowerCase1.endsWith(".jpg") && !lowerCase1.endsWith(".png")
                && !lowerCase1.endsWith(".gif") && !lowerCase1.endsWith(".jpeg")) {
            return super.simpleBuildErrorMsg("封面必须为图片文件");
        }

        String lowerCase2 = videoFile.getOriginalFilename().toLowerCase();
        if (!lowerCase2.endsWith(".mp4")) {
            return super.simpleBuildErrorMsg("视频只能为mp4文件");
        }

        if (videoCover.getSize() > Video.COVER_MAX_SIZE) {
            return super.simpleBuildErrorMsg("封面文件不能大于" + Video.COVER_MAX_SIZE / Final.FILE_SIZE_M + "M");
        }

        if (videoFile.getSize() > Video.VIDEO_MAX_SIZE) {
            return super.simpleBuildErrorMsg("视频文件不能大于" + Video.VIDEO_MAX_SIZE / Final.FILE_SIZE_M + "M");
        }

        if (t.getModuleId() == null || t.getModuleId().equals("")) {
            return super.simpleBuildErrorMsg("视频模块id不能为空");
        }
        if (t.getVideoTitle() == null || t.getVideoTitle().equals("")) {
            return super.simpleBuildErrorMsg("视频标题不能为空");
        }

        String[] strings1 = WebFileUtils.savePublicFileAtOtherServer(videoCover,
                Final.SERVER_PATH, Final.WEB_ROOT_PATH, Final.IMAGE_PATH, this.getUser().getUsername());
        String[] strings2 = WebFileUtils.savePublicFileAtOtherServer(videoFile,
                Final.SERVER_PATH, Final.WEB_ROOT_PATH, Final.VEDIO_PATH, this.getUser().getUsername());
        t.setAbsolutePath(strings2[0]);
        t.setRelativePath(Final.FILE_SERVER_PATH + strings2[1]);
        t.setCoverAbsolutePath(strings1[0]);
        t.setCoverRelativePath(Final.FILE_SERVER_PATH + strings1[1]);
        t.setCreateTime(new Date());
        t.setUsername(this.getUser().getUsername());
        t.setVideoName(videoFile.getOriginalFilename());
        this.checkService.save(t);
        t.setAbsolutePath(null);
        t.setRelativePath(null);

        return super.simpleBuildSuccessMsg("视频上传成功,正在等待管理员的审核,请留意消息通知!", t);
    }

}
