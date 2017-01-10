/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.service;

import com.vicky.common.utils.service.MybatisBaseService;
import java.util.concurrent.TimeoutException;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Vicky
 * @param <T>
 * @param <Primary>
 */
public abstract class MemcachedService<T, Primary> extends MybatisBaseService<T, Primary> {

    @Autowired
    protected MemcachedClient memcachedClient;

    /**
     * 获取日志Logger,为org.slf4j.Logger
     *
     * @return org.slf4j.Logger
     */
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    public void deleteMemcached(String key) {
        try {
            this.memcachedClient.delete(key);
        } catch (TimeoutException | InterruptedException | MemcachedException ex) {
            this.getLogger().error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    protected void setMemcached(String key, int time, Object o) {
        try {
            this.memcachedClient.set(key, time, o);
        } catch (TimeoutException | InterruptedException | MemcachedException ex) {
            this.getLogger().error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    protected <O> O getMemcached(String key) {
        try {
            return this.memcachedClient.get(key);
        } catch (TimeoutException | InterruptedException | MemcachedException ex) {
            this.getLogger().error(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

}
