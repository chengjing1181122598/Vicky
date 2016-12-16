/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.videomgr.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.common.utils.statusmsg.StatusType;
import com.vicky.modules.videomgr.utils.Manager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Vicky
 */
public class ManagerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager == null) {
            StatusMsg statusMsg = new StatusMsg(StatusType.ERROR);
            statusMsg.getMessage().put(StatusMsg.MESSAGE, "管理员没有登录");
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(statusMsg));
            response.getWriter().flush();
            return false;
        }
        return true;
    }

}
