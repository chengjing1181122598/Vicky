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

    @RequestMapping("finishRegister")
    @ResponseBody
    public StatusMsg prepareRegister(HttpServletRequest request, String activateCode) throws Exception {
        if (activateCode.equals(request.getSession().getAttribute("avtivateCode"))) {
            User user = (User) request.getSession().getAttribute("registerUser");
            this.userService.save(user);
        } else {
            throw new Exception("激活码不正确!");
        }
        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put("message", "用户激活成功!");
        return statusMsg;
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

        String avtivateCode = (int) (Math.random() * 1000000) + "";
        Mail activateMail = new ActivateMail(email, avtivateCode);
        SendEmailable sendEmailable = new SendEmailImpl();
        sendEmailable.send(activateMail);

        u.setCreateTime(new Date());
        u.setPassword(EncodePassword.encodePassword(u.getPassword()));
        request.getSession().setAttribute("registerUser", u);
        request.getSession().setAttribute("avtivateCode", avtivateCode);
        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put("message", "发送邮件成功,请前往邮箱获取验证码!");
        return statusMsg;
    }

}
