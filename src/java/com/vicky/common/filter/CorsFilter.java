/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vicky
 */
public class CorsFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        //resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "10");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, "
                + "If-Modified-Since, Pragma, Last-Modified, Cache-Control,"
                + " Expires, Content-Type, X-E4M-With,userId,token");
        //resp.setHeader("Access-Control-Allow-Credentials", "true");
        //resp.setHeader("XDomainRequestAllowed", "1");
        //response.setCharacterEncoding("utf-8");
        //request.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
    }
    
}
