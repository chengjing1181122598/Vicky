/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.modules.commentmgr.controller;

import com.vicky.common.controller.MyEntityController;
import com.vicky.common.utils.page.Page;
import com.vicky.common.utils.service.BaseService;
import com.vicky.common.utils.statusmsg.StatusMsg;
import com.vicky.modules.commentmgr.entity.CommentFloor;
import com.vicky.modules.commentmgr.service.CommentFloorService;
import com.vicky.modules.usermgr.entity.User;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("commentFloor")
public class CommentFloorController extends MyEntityController<CommentFloor, String> {

    @Autowired
    private CommentFloorService floorService;

    @Override
    protected BaseService<CommentFloor, String> getBaseService() {
        return this.floorService;
    }

    @RequestMapping("getList")
    @ResponseBody
    public Map<String, Object> getList(String videoId, Integer pageIndex, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        int index = Page.DEFAULT_PAGE_INDEX;
        int limit = Page.DEFAULT_PAGE_SIZE;
        if (pageIndex != null) {
            index = pageIndex;
        }
        if (pageSize != null) {
            limit = pageSize;
        }
        RowBounds rowBounds = new RowBounds((index - 1) * limit, limit);
        int total = this.floorService.getListCount(videoId);
        List<Map<String, Object>> maps = this.floorService.getList(videoId, rowBounds);
        map.put(Page.TOTAL_KEY, total);
        map.put(Page.DATA_KEY, maps);
        return map;
    }

    @Override
    @RequestMapping("deleteById")
    @ResponseBody
    public StatusMsg deleteById(HttpServletRequest request, HttpServletResponse response, String primaryKey) throws Exception {
        CommentFloor commentFloor = this.floorService.selectByPrimaryKey(primaryKey);
        if (!commentFloor.getUsername().equals(super.getUser().getUsername())) {
            return super.simpleBuildErrorMsg("权限不够");
        }
        return super.deleteById(request, response, primaryKey);
    }

    @Override
    @RequestMapping("save")
    @ResponseBody
    public StatusMsg save(HttpServletRequest request, HttpServletResponse response, CommentFloor t) throws Exception {
        User user = super.getUser();
        t.setCreateTime(new Date());
        t.setUsername(user.getUsername());

        return super.save(request, response, t); //To change body of generated methods, choose Tools | Templates.
    }

}
