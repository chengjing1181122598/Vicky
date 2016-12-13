/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.commentmgr.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "comment_floor")
public class CommentFloor implements Serializable {

    @Id
    @Column(name = "floor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String floorId;
    @Column(name = "username")
    private String username;
    @Column(name = "content")
    private String content;
    @Column(name = "create_time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "video_id")
    private String videoId;

    public CommentFloor() {
    }

    public CommentFloor(String videoId) {
        this.videoId = videoId;
    }

    /**
     * @return the floorId
     */
    public String getFloorId() {
        return floorId;
    }

    /**
     * @param floorId the floorId to set
     */
    public void setFloorId(String floorId) {
        this.floorId = floorId;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
     * @return the videoId
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * @param videoId the videoId to set
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
