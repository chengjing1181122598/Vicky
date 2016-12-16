/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.usermgr.entity;

import com.vicky.common.finalpackage.Final;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Vicky
 */
@Entity
@Table(name = "user")
public class User implements Serializable, Cloneable {

    public static final String DEFAULT_HEAD_RELATIVE_PATH = "/vicky_file/default/user_head.png";
    public static final String DEFAULT_HEAD_ABSOLUTE_PATH = "/F:/nginx_file/html/vicky_file/default/user_head.png";

    public static final String MALE = "男";
    public static final String FEMALE = "女";
    public static final String SECRET = "保密";

    public static int HEAD_MAX_SIZE = Final.FILE_SIZE_M * 2;

    @Id
    @Column(name = "username")
    @Pattern(regexp = "[\u4e00-\u9fa5\u0800-\u4e00\\w]+", message = "用户名只能为中文英文日文数字")
    @Length(min = 2, max = 10, message = "用户名在2位到8位之间")
    private String username;
    @Column(name = "email")
    @Pattern(regexp = "[\\w]+@[\\w]+\\.[\\w]+", message = "请输入正确的邮箱地址")
    private String email;
    @Column(name = "create_time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "password")
    @Pattern(regexp = "\\w*[a-zA-Z]+\\w*", message = "密码为数字字母,必须含有字母")
    @Length(min = 8, max = 30, message = "密码在8位到30位之间")
    private String password;
    @Column(name = "sex")
    private String sex;
    @Column(name = "signature")
    private String signature;
    @Column(name = "image_relative_path")
    private String relativePath;
    @Column(name = "image_absolute_path")
    private String absolutePath;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return the relativePath
     */
    public String getRelativePath() {
        return relativePath;
    }

    /**
     * @param relativePath the relativePath to set
     */
    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    /**
     * @return the absolutePath
     */
    public String getAbsolutePath() {
        return absolutePath;
    }

    /**
     * @param absolutePath the absolutePath to set
     */
    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
