/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.TreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池常量
 *
 * @author Vicky
 */
public final class ThreadPool {

    public static final ExecutorService FILE_THREADPOOL = Executors.newFixedThreadPool(10);
    public static final ExecutorService SEND_EMAIL_THREADPOOL = Executors.newFixedThreadPool(10);
}
