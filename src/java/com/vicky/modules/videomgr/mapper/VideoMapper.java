/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.videomgr.mapper;

import com.vicky.modules.videomgr.entity.Video;
import java.util.List;
import java.util.Map;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Vicky
 */
public interface VideoMapper extends Mapper<Video> {

    public List<Map> getPerSize();

    public List<Map> getSlipeData();
}
