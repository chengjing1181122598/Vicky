/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.videomgr.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Vicky
 */
@Entity
@Table
public class VideoCheck implements Serializable {

    @Id
    @Column(name = "video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String videoId;
    @Column(name = "module_id")
    private String moduleId;
    @Column(name = "absolute_path")
    private String absolutePath;
    @Column(name = "relative_path")
    private String relativePath;
    @Column(name = "video_name")
    private String videoName;
    @Column(name = "video_title")
    @Length(min = 1, max = 50, message = "标题长度在1到50位之间")
    private String videoTitle;
    @Column(name = "username")
    private String username;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "cover_absolute_path")
    private String coverAbsolutePath;
    @Column(name = "cover_relative_path")
    private String coverRelativePath;
    @Column(name = "video_explain")
    @Length(min = 1, max = 255, message = "标题长度在1到255位之间")
    private String videoExplain;

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
     * @return the videoName
     */
    public String getVideoName() {
        return videoName;
    }

    /**
     * @param videoName the videoName to set
     */
    public void setVideoName(String videoName) {
        this.videoName = videoName;
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
     * @return the coverAbsolutePath
     */
    public String getCoverAbsolutePath() {
        return coverAbsolutePath;
    }

    /**
     * @param coverAbsolutePath the coverAbsolutePath to set
     */
    public void setCoverAbsolutePath(String coverAbsolutePath) {
        this.coverAbsolutePath = coverAbsolutePath;
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
     * @return the videoExplain
     */
    public String getVideoExplain() {
        return videoExplain;
    }

    /**
     * @param videoExplain the videoExplain to set
     */
    public void setVideoExplain(String videoExplain) {
        this.videoExplain = videoExplain;
    }

    /**
     * @return the moduleId
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * @param moduleId the moduleId to set
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

}
