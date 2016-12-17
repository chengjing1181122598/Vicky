/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.commentmgr.mapper;

import com.vicky.modules.commentmgr.entity.CommentFloor;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author Administrator
 */
public interface CommentFloorMapper extends Mapper<CommentFloor> {

    @SelectProvider(type = CommentFloorSqlBuilder.class, method = "getList")
    public List<Map<String, Object>> getList(String videoId, RowBounds rowBounds);

    @SelectProvider(type = CommentFloorSqlBuilder.class, method = "getListCount")
    public int getListCount(String videoId);

    class CommentFloorSqlBuilder {

        public String getList(String videoId) {
            return new SQL() {
                {
                    SELECT("t1.username username , t1.image_relative_path relativePath , t2.content content , t2.create_time createTime");
                    FROM("user t1");
                    FROM("comment_floor t2");
                    WHERE("t2.video_id = #{videoId} and t2.username = t1.username");
                    ORDER_BY("t2.create_time asc");
                }
            }.toString();
        }

        public String getListCount(String videoId) {
            return new SQL() {
                {
                    SELECT("count(*)");
                    FROM("user t1");
                    FROM("comment_floor t2");
                    WHERE("t2.video_id = #{videoId} and t2.username = t1.username");
                }
            }.toString();
        }
    }
}
