/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.usermgr.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Vicky
 */
@Entity
@Table(name = "user_head")
public class UserHead implements Serializable {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "image_relative_path")
    private String imageRelativePath;
    @Column(name = "image_absolute_path")
    private String imageAbsolutePath;

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
     * @return the imageRelativePath
     */
    public String getImageRelativePath() {
        return imageRelativePath;
    }

    /**
     * @param imageRelativePath the imageRelativePath to set
     */
    public void setImageRelativePath(String imageRelativePath) {
        this.imageRelativePath = imageRelativePath;
    }

    /**
     * @return the imageAbsolutePath
     */
    public String getImageAbsolutePath() {
        return imageAbsolutePath;
    }

    /**
     * @param imageAbsolutePath the imageAbsolutePath to set
     */
    public void setImageAbsolutePath(String imageAbsolutePath) {
        this.imageAbsolutePath = imageAbsolutePath;
    }


}
