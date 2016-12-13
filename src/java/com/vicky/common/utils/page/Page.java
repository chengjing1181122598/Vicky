/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.page;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页参数,预定大于配置
 *
 * @author Vicky
 */
public class Page {

    public static final String TOTAL_KEY = "total";//map键：分页查询总共多少条
    public static final String DATA_KEY = "data";//map键：分页查询List数据

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_INDEX = 1;
    public static final String DEFAULT_HTTP_REQUEST_PAGE_SIZE_PARAM_NAME = "pageSize";
    public static final String DEFAULT_HTTP_REQUEST_PAGE_INDEX_PARAM_NAME = "pageIndex";

    private Integer pageSize;
    private Integer pageIndex;
    private Integer pageOffset;

    private Page() {
    }

    public Page(Integer pageIndex, Integer pageSize) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.pageOffset = (pageIndex - 1) * pageSize;
    }

    /**
     * 解析HttpServletRequest获得分页
     *
     * @param request HttpServletRequest
     * @return 分页
     */
    public static Page getPageFromHttpRequest(HttpServletRequest request) {
        Page page = new Page();
        //设置page大小
        String pageSizeString = request.getParameter(Page.DEFAULT_HTTP_REQUEST_PAGE_SIZE_PARAM_NAME);
        if (pageSizeString == null || pageSizeString.length() == 0) {
            page.setPageSize(Page.DEFAULT_PAGE_SIZE);
        } else {
            page.setPageSize(Integer.parseInt(pageSizeString));
        }

        //设置page偏移量
        String pageIndexString = request.getParameter(Page.DEFAULT_HTTP_REQUEST_PAGE_INDEX_PARAM_NAME);
        if (pageIndexString == null || pageIndexString.length() == 0) {
            page.setPageIndex(Page.DEFAULT_PAGE_INDEX);
        } else {
            page.setPageIndex(Integer.parseInt(pageIndexString));
        }
        page.setPageOffset((page.getPageIndex() - 1) * page.pageSize);

        return page;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageIndex
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * @return the pageOffset
     */
    public Integer getPageOffset() {
        return pageOffset;
    }

    /**
     * @param pageOffset the pageOffset to set
     */
    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }

}
