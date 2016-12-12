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
import com.vicky.modules.collectmgr.entity.CollectUser;
import com.vicky.modules.collectmgr.service.CollectUserService;
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
@RequestMapping("collectUser")
public class CollectUserController extends MyEntityController<CollectUser, String> {

    @Autowired
    private CollectUserService collectUserService;

    @Override
    protected BaseService<CollectUser, String> getBaseService() {
        return this.collectUserService;
    }

//    @RequestMapping("getAll")
//    @ResponseBody
//    public Map<String, Object> getAll(Integer pageIndex, Integer pageSize) {
//
//    }

    @Override
    @RequestMapping("deleteByid")
    @ResponseBody
    public StatusMsg deleteById(HttpServletRequest request, HttpServletResponse response, String primaryKey) throws Exception {
        CollectUser collectUser = this.collectUserService.selectByPrimaryKey(primaryKey);
        if (!collectUser.getCollectingUsername().equals(super.getUser().getUsername())) {
            throw new StatusMsgException("权限不够");
        }
        return super.deleteById(request, response, primaryKey); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RequestMapping("save")
    @ResponseBody
    public StatusMsg save(HttpServletRequest request, HttpServletResponse response, CollectUser t) throws Exception {
        t.setCollectingUsername(super.getUser().getUsername());
        t.setCreateTime(new Date());
        return super.save(request, response, t); //To change body of generated methods, choose Tools | Templates.
    }

}
