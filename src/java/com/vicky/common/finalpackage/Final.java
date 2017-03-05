/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.finalpackage;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vicky
 */
public class Final {
    
    public static final String SERVER_PATH = "/usr/share/nginx/html/vicky_html";
    public static final String WEB_ROOT_PATH = "vicky_file";
    public static final String IMAGE_PATH = "image";
    public static final String VEDIO_PATH = "vedio";

    public static final String FILE_SERVER_PATH = "http://119.29.34.100";

//    public static final String SERVER_PATH = "/F:/nginx_file/html";
//    public static final String WEB_ROOT_PATH = "vicky_file";
//    public static final String IMAGE_PATH = "image";
//    public static final String VEDIO_PATH = "vedio";
//
//    public static final String FILE_SERVER_PATH = "http://localhost:8084";

    public static final int FILE_SIZE_M = 1024 * 1024;
    public static final int FILE_SIZE_K = 1024;
    public static final int FILE_SIZE_G = 1024 * 1024 * 1024;
    
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
