/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.DealFile;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程保存文件
 *
 * @author Vicky
 */
public class WebFileSavePart implements Runnable {

    /**
     * 获取日志Logger,为org.slf4j.Logger
     *
     * @return org.slf4j.Logger
     */
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    private Part part;
    private String path;
    private String filename;

    public WebFileSavePart(Part part, String path, String filename) {
        this.part = part;
        this.path = path;
        this.filename = filename;
    }

    @Override
    public void run() {
        File targetFile = new File(path, filename);
        try {
            if (!targetFile.exists()) {
                targetFile.getParentFile().mkdirs();
                targetFile.createNewFile();
            }
            this.part.write(path + "/" + filename);
            this.part.delete();
        } catch (IOException exception) {
            this.getLogger().error("toString：" + exception.toString());
            this.getLogger().error("getMessage：" + exception.getMessage());
            this.getLogger().error("exception：", exception);
            exception.printStackTrace();
        }
    }

    /**
     * @return the multipartFile
     */
    public Part getMultipartFile() {
        return part;
    }

    /**
     * @param part
     */
    public void setMultipartFile(Part part) {
        this.part = part;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
