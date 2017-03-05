/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.rankmgr.controller;

import com.vicky.common.controller.MyEntityController;
import com.vicky.common.utils.service.BaseService;
import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.modules.rankmgr.entity.VideoPlayNumber;
import com.vicky.modules.rankmgr.service.PlayNumberService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 *
 * @author Vicky
 */
@Controller
@RequestMapping("videoPlayNumber")
public class VideoPlayNumberController extends MyEntityController<VideoPlayNumber, String> {

    @Autowired
    private PlayNumberService playNumberService;

    @Override
    protected BaseService<VideoPlayNumber, String> getBaseService() {
        return playNumberService;
    }

    @RequestMapping("getPlayNumber")
    @ResponseBody
    public StatusMsg getPlayNumber(String videoId) {
        VideoPlayNumber vpn = new VideoPlayNumber();
        vpn.setVideoId(videoId);
        return super.simpleBuildSuccessMsg(this.playNumberService.selectOne(vpn));
    }

    @RequestMapping("increment")
    @ResponseBody
    public WebAsyncTask<StatusMsg> increment(HttpServletRequest request, String videoId) {
        WebAsyncTask<StatusMsg> task = new WebAsyncTask(10000, () -> {
            return super.simpleBuildSuccessMsg(this.playNumberService.increment(request, videoId));
        });
        task.onTimeout(() -> {
            return super.simpleBuildErrorMsg("响应超时");
        });
        return task;
    }

}
