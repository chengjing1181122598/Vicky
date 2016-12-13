/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 通用BaseService
 *
 * @author Vicky
 * @param <T>
 * @param <PrimaryKey>
 */
public interface BaseService<T, PrimaryKey> {

    public T selectByPrimaryKey(PrimaryKey primaryKey);

    public T selectOne(T t);

    public void update(PrimaryKey primaryKey, HttpServletRequest request) throws Exception;

    public void deleteById(PrimaryKey primaryKey) throws Exception;

    public void save(T t);

    public Map<String, Object> getPageData(HttpServletRequest request) throws Exception;
}
