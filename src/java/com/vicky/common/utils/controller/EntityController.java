/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.controller;

import com.vicky.common.utils.service.BaseService;
import com.vicky.common.utils.statusmsg.StatusMsg;
import java.io.Serializable;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 简单实体cotroller,实际上用处并不大,功能也较为简单
 *
 * @author Vicky
 * @param <T>
 * @param <PrimaryKey>
 */
public abstract class EntityController<T, PrimaryKey extends Serializable> extends BaseController {

    /**
     * BaseService,子类必须实现该方法注入bean
     * <p>
     * @return 一个BaseService或者其子类,该类所有方法依赖这个返回值
     */
    protected abstract BaseService<T, PrimaryKey> getBaseService();

    /**
     * 根据主键获得实体,子类必须重写主键参数以获得正确的绑定
     * <p>
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param primaryKey 实体主键
     * @return 实体
     * @throws java.lang.Exception
     */
    public T getById(HttpServletRequest request, HttpServletResponse response, PrimaryKey primaryKey) throws Exception {
        return this.getBaseService().selectByPrimaryKey(primaryKey);
    }

    /**
     * 根据实体属性获得唯一实体,实体不唯一将抛出异常
     * <p>
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param t 实体
     * @return 实体
     * @throws java.lang.Exception
     */
    public T getByEntity(HttpServletRequest request, HttpServletResponse response, T t) throws Exception {
        return this.getBaseService().selectOne(t);
    }

    /**
     * 根据主键修改实体,修改参数约定大于配置
     * <p>
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param primaryKey 实体主键
     * @return Status响应消息类
     * @throws java.lang.Exception
     * @see
     * com.vicky.utils.update.UpdateProperty#getUpdatePropertyListFromHttpRequest
     */
    public StatusMsg updateById(HttpServletRequest request, HttpServletResponse response, PrimaryKey primaryKey)
            throws Exception {
        this.getBaseService().update(primaryKey, request);
        T t = this.getBaseService().selectByPrimaryKey(primaryKey);
        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put("message", "修改信息成功,修改后的实体如下：");
        statusMsg.getMessage().put("entity", t);
        return statusMsg;
    }

    /**
     * 根据主键删除实体
     * <p>
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param primaryKey 实体主键
     * @return Status响应消息类
     * @throws java.lang.Exception
     */
    public StatusMsg deleteById(HttpServletRequest request, HttpServletResponse response, PrimaryKey primaryKey) throws Exception {
        T t = this.getBaseService().selectByPrimaryKey(primaryKey);
        this.getBaseService().deleteById(primaryKey);
        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put("message", "删除成功,删除实体如下：");
        statusMsg.getMessage().put("entity", t);
        return statusMsg;
    }

    /**
     * 保存实体
     * <p>
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param t 实体
     * @return Status响应消息类
     * @throws java.lang.Exception
     */
    public StatusMsg save(HttpServletRequest request, HttpServletResponse response, T t) throws Exception {
        this.getBaseService().save(t);
        StatusMsg statusMsg = new StatusMsg(StatusMsg.SUCCESS);
        statusMsg.getMessage().put("message", "保存成功,保存的实体如下：");
        statusMsg.getMessage().put("entity", t);
        return statusMsg;
    }

    /**
     * 分页查询实体,约定大于配置
     * <p>
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return Map类型,键值对为total：实体总数,data：List实体集合
     * @throws java.lang.Exception
     */
    public Map<String, Object> getPageData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.getBaseService().getPageData(request);
    }

}
