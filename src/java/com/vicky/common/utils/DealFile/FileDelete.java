/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.DealFile;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程删除文件
 *
 * @author Vicky
 */
public class FileDelete implements Runnable {

    /**
     * 获取日志Logger,为org.slf4j.Logger
     *
     * @return org.slf4j.Logger
     */
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    private File file;

    public FileDelete(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        if (!this.file.exists()) {
            System.out.println(this.getClass().getName() + "：文件不存在");
            this.getLogger().error(this.getClass().getName() + "：文件不存在");
        } else {
            this.file.delete();
        }
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

}
