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
@Table(name = "collect_user")
public class CollectUser implements Serializable {

    @Id
    @Column(name = "collect_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String collectUserId;
    @Column(name = "collecting_username")
    private String collectingUsername;
    @Column(name = "collected_username")
    private String collectedUsername;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return the collectUserId
     */
    public String getCollectUserId() {
        return collectUserId;
    }

    /**
     * @param collectUserId the collectUserId to set
     */
    public void setCollectUserId(String collectUserId) {
        this.collectUserId = collectUserId;
    }

    /**
     * @return the collectingUsername
     */
    public String getCollectingUsername() {
        return collectingUsername;
    }

    /**
     * @param collectingUsername the collectingUsername to set
     */
    public void setCollectingUsername(String collectingUsername) {
        this.collectingUsername = collectingUsername;
    }

    /**
     * @return the collectedUsername
     */
    public String getCollectedUsername() {
        return collectedUsername;
    }

    /**
     * @param collectedUsername the collectedUsername to set
     */
    public void setCollectedUsername(String collectedUsername) {
        this.collectedUsername = collectedUsername;
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
}
