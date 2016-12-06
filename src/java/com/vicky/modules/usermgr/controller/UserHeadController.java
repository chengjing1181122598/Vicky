/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.usermgr.controller;

import com.vicky.common.utils.controller.EntityController;
import com.vicky.common.utils.service.BaseService;
import com.vicky.modules.usermgr.entity.UserHead;
import com.vicky.modules.usermgr.service.UserHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Vicky
 */
@Controller
@RequestMapping("userHead")
public class UserHeadController extends EntityController<UserHead, String> {

    @Autowired
    private UserHeadService userHeadService;

    @Override
    protected BaseService<UserHead, String> getBaseService() {
        return this.userHeadService;
    }

}
