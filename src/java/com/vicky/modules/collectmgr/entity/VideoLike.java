/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.collectmgr.entity;

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
@Table(name = "video_like")
public class VideoLike implements Serializable {

    @Id
    @Column(name = "video_like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String videoLikeId;
    @Column(name = "username")
    private String username;
    @Column(name = "video_id")
    private String videoId;
    @Column(name = "cover_relative_path")
    private String coverRelativePath;
    @Column(name = "video_title")
    private String videoTitle;
    @Column(name = "like_time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date likeTime;

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

    /**
     * @return the coverRelativePath
     */
    public String getCoverRelativePath() {
        return coverRelativePath;
    }

    /**
     * @param coverRelativePath the coverRelativePath to set
     */
    public void setCoverRelativePath(String coverRelativePath) {
        this.coverRelativePath = coverRelativePath;
    }

    /**
     * @return the videoTitle
     */
    public String getVideoTitle() {
        return videoTitle;
    }

    /**
     * @param videoTitle the videoTitle to set
     */
    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    /**
     * @return the likeTime
     */
    public Date getLikeTime() {
        return likeTime;
    }

    /**
     * @param likeTime the likeTime to set
     */
    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }

    /**
     * @return the videoLikeId
     */
    public String getVideoLikeId() {
        return videoLikeId;
    }

    /**
     * @param videoLikeId the videoLikeId to set
     */
    public void setVideoLikeId(String videoLikeId) {
        this.videoLikeId = videoLikeId;
    }
}
