/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.commentmgr.service;

import com.vicky.common.utils.service.MybatisBaseService;
import com.vicky.modules.commentmgr.entity.CommentFloor;
import com.vicky.modules.commentmgr.mapper.CommentFloorMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Administrator
 */
@Service
public class CommentFloorService extends MybatisBaseService<CommentFloor, String> {
    
    @Autowired
    private CommentFloorMapper floorMapper;
    
    @Override
    protected Mapper<CommentFloor> getMapper() {
        return this.floorMapper;
    }
    
    public List<Map<String, Object>> getList(String videoId, RowBounds rowBounds) {
        return this.floorMapper.getList(videoId, rowBounds);
    }
    
    public int getListCount(String videoId) {
        return this.floorMapper.getListCount(videoId);
    }
    
}
