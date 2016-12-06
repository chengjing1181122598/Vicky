/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.usermgr.controller;

import com.vicky.common.utils.controller.EntityController;
import com.vicky.common.utils.encodepwd.EncodePassword;
import com.vicky.common.utils.sendemail.Mail;
import com.vicky.common.utils.sendemail.SendEmailImpl;
import com.vicky.common.utils.sendemail.SendEmailable;
import com.vicky.common.utils.service.BaseService;
import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.modules.usermgr.entity.User;
import com.vicky.modules.usermgr.service.UserService;
import com.vicky.modules.usermgr.utils.ActivateMail;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Vicky
 */
@Controller
@RequestMapping("user")
public class UserController extends EntityController<User, String> {

    @Autowired
    private UserService userService;

    @Override
    protected BaseService<User, String> getBaseService() {
        return this.userService;
    }

    @RequestMapping("prepareRegister")
    @ResponseBody
    public StatusMsg prepareRegister(HttpServletRequest request, User u) throws Exception {
        String username = u.getUsername();
        String email = u.getEmail();
        if (this.userService.selectByPrimaryKey(username) != null) {
            throw new Exception("该用户已存在");
        }
        User queryUser = new User();
        queryUser.setEmail(email);
        if (this.userService.selectOne(queryUser) != null) {
            throw new Exception("该邮箱已注册");
        }

        u.setCreateTime(new Date());
        u.setPassword(EncodePassword.encodePassword(u.getPassword()));
        request.getSession().setAttribute("registerUser", u);

        String avtivateCode = (int) (Math.random() * 10000000) + "";
        request.getSession().setAttribute("avtivateCode", avtivateCode);
        Mail activateMail = new ActivateMail(email, avtivateCode);
        SendEmailable sendEmailable = new SendEmailImpl();
        sendEmailable.send(activateMail);
        StatusMsg statusMsg = new StatusMsg();
        statusMsg.setStatus(StatusMsg.SUCCESS);
        statusMsg.getMessage().put("message", "发送邮件成功,请前往邮箱获取验证码!");
        return statusMsg;
    }

}
