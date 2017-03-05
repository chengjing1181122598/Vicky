/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.rankmgr.mapper;

import com.vicky.modules.rankmgr.entity.VideoPlayNumber;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Vicky
 */
public interface PlayNumberMapper extends Mapper<VideoPlayNumber> {

    @Update("update video_play_number set number = #{number} , version = version + 1 where play_number_id = #{playNumberId} and version = #{version}")
    public int increment(VideoPlayNumber videoPlayNumber);
}
