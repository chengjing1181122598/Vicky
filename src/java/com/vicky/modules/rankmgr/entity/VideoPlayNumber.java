/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.rankmgr.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Vicky
 */
@Entity
@Table(name = "video_play_number")
public class VideoPlayNumber implements Serializable {

    public static final String MEMCACHED_IP_PREFFIX = "ip_";
    public static final int MEMCACHED_IP_TIMEOUT = 24 * 60 * 60;

    @Id
    @Column(name = "play_number_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String playNumberId;
    @Column(name = "video_id")
    private String videoId;
    @Column(name = "number")
    private Long number;
    @Column(name = "version")
    private Long version;

    public VideoPlayNumber() {
    }

    public VideoPlayNumber(String videoId) {
        this.videoId = videoId;
    }

    /**
     * @return the playNumberId
     */
    public String getPlayNumberId() {
        return playNumberId;
    }

    /**
     * @param playNumberId the playNumberId to set
     */
    public void setPlayNumberId(String playNumberId) {
        this.playNumberId = playNumberId;
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
     * @return the number
     */
    public Long getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Long number) {
        this.number = number;
    }

    /**
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Long version) {
        this.version = version;
    }
}
