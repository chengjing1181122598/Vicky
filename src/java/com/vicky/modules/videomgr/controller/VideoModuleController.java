/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.videomgr.controller;

import com.vicky.common.utils.controller.EntityController;
import com.vicky.common.utils.service.BaseService;
import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.common.utils.statusmsg.StatusMsgException;
import com.vicky.modules.videomgr.entity.VideoModule;
import com.vicky.modules.videomgr.service.VideoModuleService;
import com.vicky.modules.videomgr.utils.Manager;
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
 * @author Vicky
 */
@Controller
@RequestMapping("videoModule")
public class VideoModuleController extends EntityController<VideoModule, String> {

    @Autowired
    private VideoModuleService moduleService;

    @Override
    protected BaseService<VideoModule, String> getBaseService() {
        return this.moduleService;
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

    @Override
    @RequestMapping("updateById")
    @ResponseBody
    public StatusMsg updateById(HttpServletRequest request, HttpServletResponse response, String primaryKey) throws Exception {
        return super.updateById(request, response, primaryKey);
    }

    @RequestMapping("login")
    @ResponseBody
    public StatusMsg login(HttpServletRequest request, Manager manager) throws Exception {
        if (!manager.getUsername().equals(Manager.DEFAULT_MANAGER_USERNAME)
                || !manager.getPassword().equals(Manager.DEFAULT_MANAGER_PASSWORD)) {
            throw new StatusMsgException("管理员账号密码不正确!");
        } else {
            request.getSession().setAttribute("manager", manager);
        }
        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put(StatusMsg.MESSAGE, "管理员登录成功!");
        return statusMsg;
    }

    @Override
    @RequestMapping("save")
    @ResponseBody
    public StatusMsg save(HttpServletRequest request, HttpServletResponse response, VideoModule t) throws Exception {
        t.setModuleId(null);
        t.setCreateTime(new Date());
        this.moduleService.save(t);
        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put(StatusMsg.MESSAGE, "保存视频模块成功");
        statusMsg.getMessage().put(StatusMsg.ENTITY, t);
        return statusMsg;
    }

}
