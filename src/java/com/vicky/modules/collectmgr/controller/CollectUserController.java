/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.collectmgr.controller;

import com.vicky.common.utils.controller.EntityController;
import com.vicky.common.utils.service.BaseService;
import com.vicky.modules.collectmgr.entity.CollectUser;
import com.vicky.modules.collectmgr.service.CollectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("collectUser")
public class CollectUserController extends EntityController<CollectUser, String> {

    @Autowired
    private CollectUserService collectUserService;

    @Override
    protected BaseService<CollectUser, String> getBaseService() {
        return this.collectUserService;
    }

}
