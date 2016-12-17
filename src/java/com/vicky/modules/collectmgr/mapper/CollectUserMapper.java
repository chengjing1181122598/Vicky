/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.collectmgr.mapper;

import com.vicky.modules.collectmgr.entity.CollectUser;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Administrator
 */
public interface CollectUserMapper extends Mapper<CollectUser> {

    public List<Map<String, Object>> getList(String username, RowBounds rowBounds);

    public int getListCount(String username);
}
