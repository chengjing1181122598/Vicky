/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.controller;

import com.vicky.common.utils.controller.EntityController;
import com.vicky.modules.usermgr.entity.User;
import com.vicky.modules.videomgr.utils.Manager;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Vicky
 * @param <T>
 * @param <Primary>
 */
public abstract class MyEntityController<T, Primary> extends EntityController<T, Primary> {

    @Autowired
    private HttpServletRequest request;

    protected User getUser() {
        return (User) this.request.getSession().getAttribute("user");
    }

    protected Manager getManager() {
        return (Manager) this.request.getSession().getAttribute("manager");
    }

}