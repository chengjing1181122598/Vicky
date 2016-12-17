/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.usermgr.controller;

import com.vicky.common.controller.MyEntityController;
import com.vicky.common.finalpackage.Final;
import com.vicky.common.utils.DealFile.WebFileUtils;
import com.vicky.common.utils.encodepwd.EncodePassword;
import com.vicky.common.utils.sendemail.Mail;
import com.vicky.common.utils.sendemail.SendEmailImpl;
import com.vicky.common.utils.sendemail.SendEmailable;
import com.vicky.common.utils.service.BaseService;
import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.common.utils.statusmsg.StatusMsgException;
import com.vicky.common.utils.statusmsg.StatusType;
import com.vicky.modules.usermgr.entity.User;
import com.vicky.modules.usermgr.service.UserService;
import com.vicky.modules.usermgr.utils.ActivateMail;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Vicky
 */
@Controller
@RequestMapping("user")
public class UserController extends MyEntityController<User, String> {

    @Autowired
    private UserService userService;

    @Override
    protected BaseService<User, String> getBaseService() {
        return this.userService;
    }

    protected User getProtectedUser(User user) throws CloneNotSupportedException {
        User returnUser = (User) user.clone();
        returnUser.setPassword(null);
        return returnUser;
    }

    @RequestMapping("updateHead")
    @ResponseBody
    public StatusMsg updateHead(@RequestParam(value = "headImage") MultipartFile file) throws IOException, CloneNotSupportedException, StatusMsgException {
        User user = this.getUser();
        if (file.getSize() > User.HEAD_MAX_SIZE) {
            throw new StatusMsgException("上传头像不能大于" + User.HEAD_MAX_SIZE / Final.FILE_SIZE_M + "M");
        }

        String lowerCase = file.getOriginalFilename().toLowerCase();
        if (!lowerCase.endsWith(".jpg") && !lowerCase.endsWith(".png")
                && !lowerCase.endsWith(".gif") && !lowerCase.endsWith(".jpeg")) {
            throw new StatusMsgException("请上传图片文件");
        }

        file.getInputStream().available();

        String[] paths = WebFileUtils.savePublicFileAtOtherServer(file,
                Final.SERVER_PATH, Final.WEB_ROOT_PATH, Final.IMAGE_PATH, user.getUsername());

        if (!user.getAbsolutePath().equals(User.DEFAULT_HEAD_ABSOLUTE_PATH)) {
            File frontHeadImage = new File(user.getAbsolutePath());
            frontHeadImage.delete();
        }

        user.setAbsolutePath(paths[0]);
        user.setRelativePath(paths[1]);

        this.userService.updateSelective(user);

        return super.simpleBuildMsg(StatusType.SUCCESS, "修改头像成功", this.getProtectedUser(user));
    }

    @RequestMapping("logout")
    @ResponseBody
    public StatusMsg logout() {
        super.request.getSession().invalidate();
        return super.simpleBuildMsg(StatusType.SUCCESS, "用户退出成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public StatusMsg update(User updateUser) throws StatusMsgException, CloneNotSupportedException {
        User user = this.getUser();
        if (updateUser.getSex() != null && !updateUser.getSex().equals("")) {
            if (!updateUser.getSex().equals(User.FEMALE)
                    && !updateUser.getSex().equals(User.MALE) && !updateUser.getSex().equals(User.SECRET)) {
                throw new StatusMsgException("性别只能为：'男','女'或者'保密'");
            }
            user.setSex(updateUser.getSex());
        }
        if (updateUser.getSignature() != null && !updateUser.getSignature().equals("")) {
            user.setSignature(updateUser.getSignature());
        }
        this.userService.updateSelective(user);

        return super.simpleBuildMsg(StatusType.SUCCESS, "修改信息成功", this.getProtectedUser(user));
    }

    @RequestMapping("updatePWD")
    @ResponseBody
    public StatusMsg updatePWD(String frontPWD, String afterPWD) throws StatusMsgException {
        User user = this.getUser();
        if (!EncodePassword.checkPassword(frontPWD, user.getPassword())) {
            throw new StatusMsgException("原来密码不正确");
        } else {

            if (afterPWD.length() < 8 || afterPWD.length() > 30 || !afterPWD.matches("\\w*[a-zA-Z]+\\w*")) {
                throw new StatusMsgException("密码长度8位到30位,只能为数字字母,必须含有字母");
            }
            user.setPassword(EncodePassword.encodePassword(afterPWD));
            this.userService.updateSelective(user);
        }
        return super.simpleBuildMsg(StatusType.SUCCESS, "修改密码成功");
    }

    @Override
    @RequestMapping("getById")
    @ResponseBody
    public StatusMsg getById(HttpServletRequest request, HttpServletResponse response, String username) {
        User user = this.userService.selectByPrimaryKey(username);
        user.setPassword(null);
        return super.simpleBuildMsg(StatusType.SUCCESS, user);
    }

    @RequestMapping("getUser")
    @ResponseBody
    public StatusMsg getUserMessage() throws CloneNotSupportedException, StatusMsgException {
        return super.simpleBuildMsg(StatusType.SUCCESS, this.getProtectedUser(this.getUser()));
    }

    @RequestMapping("login")
    @ResponseBody
    public StatusMsg login(User paramUser) throws Exception {
        User user;
        if (paramUser.getUsername().contains("@")) {
            user = this.userService.selectOne(new User(paramUser.getUsername()));
        } else {
            user = this.userService.selectByPrimaryKey(paramUser.getUsername());
        }
        if (user == null) {
            throw new StatusMsgException("找不到用户,请检查用户名是否正确");
        }
        if (!EncodePassword.checkPassword(paramUser.getPassword(), user.getPassword())) {
            throw new StatusMsgException("密码错误");
        }
        user = this.userService.selectByPrimaryKey(user.getUsername());
        super.request.getSession().setAttribute("user", user);
        return super.simpleBuildMsg(StatusType.SUCCESS, this.getProtectedUser(user));
    }

    @RequestMapping("finishRegister")
    @ResponseBody
    public StatusMsg prepareRegister(String activateCode) throws Exception {
        System.out.println(super.request.getSession().getAttribute("avtivateCode"));
        if (activateCode.equals(super.request.getSession().getAttribute("avtivateCode"))) {
            User user = (User) super.request.getSession().getAttribute("registerUser");
            this.userService.save(user);
            super.request.getSession().setAttribute("user", user);
            return super.simpleBuildMsg(StatusType.SUCCESS, "用户激活成功!", this.getProtectedUser(user));
        } else {
            throw new StatusMsgException("激活码不正确!");
        }
    }

    @RequestMapping("prepareRegister")
    @ResponseBody
    public StatusMsg prepareRegister(@Valid User u, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            FieldError error = result.getFieldError();
            throw new StatusMsgException(error.getDefaultMessage());
        }

        super.request.getSession(true);
        String username = u.getUsername();
        String email = u.getEmail();
        if (this.userService.selectByPrimaryKey(username) != null) {
            throw new StatusMsgException("该用户已存在");
        }
        User queryUser = new User();
        queryUser.setEmail(email);
        if (this.userService.selectOne(queryUser) != null) {
            throw new StatusMsgException("该邮箱已注册");
        }

        String avtivateCode = (int) (Math.random() * 900000 + 100000) + "";
        Mail activateMail = new ActivateMail(email, avtivateCode);
        SendEmailable sendEmailable = new SendEmailImpl();
        sendEmailable.send(activateMail);

        u.setCreateTime(new Date());
        u.setPassword(EncodePassword.encodePassword(u.getPassword()));
        u.setAbsolutePath(User.DEFAULT_HEAD_ABSOLUTE_PATH);
        u.setRelativePath(User.DEFAULT_HEAD_RELATIVE_PATH);

        super.request.getSession().setAttribute("registerUser", u);
        super.request.getSession().setAttribute("avtivateCode", avtivateCode);
        System.out.println(super.request.getSession().getAttribute("avtivateCode"));
        return super.simpleBuildMsg(StatusType.SUCCESS, "发送邮件成功,请前往邮箱获取激活码!",this.getProtectedUser(u));
    }

}
