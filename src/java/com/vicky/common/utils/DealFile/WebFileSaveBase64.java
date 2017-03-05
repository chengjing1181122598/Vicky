/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.DealFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

/**
 * 线程保存文件
 *
 * @author Vicky
 */
public class WebFileSaveBase64 implements Runnable {

    /**
     * 获取日志Logger,为org.slf4j.Logger
     *
     * @return org.slf4j.Logger
     */
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    private String base64Str;
    private String path;
    private String filename;

    public WebFileSaveBase64(String base64Str, String path, String filename) {
        this.base64Str = base64Str;
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
            //以流的方式写入文件
            this.base64Str = this.base64Str.replace("data:image/jpeg;base64,", "");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bs = decoder.decodeBuffer(this.base64Str);
            for (int i = 0; i < bs.length; ++i) {
                if (bs[i] < 0) {//调整异常数据  
                    bs[i] += 256;
                }
            }
            int bufferSize;
            if (bs.length > Integer.MAX_VALUE) {
                bufferSize = Integer.MAX_VALUE;
            } else {
                bufferSize = bs.length;
            }

            FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream, bufferSize);
            bos.write(bs);
            bos.close();

            fileOutputStream.close();
        } catch (IOException exception) {
            this.getLogger().error("toString：" + exception.toString());
            this.getLogger().error("getMessage：" + exception.getMessage());
            this.getLogger().error("exception：", exception);
            exception.printStackTrace();
        }
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

    /**
     * @return the base64Str
     */
    public String getBase64Str() {
        return base64Str;
    }

    /**
     * @param base64Str the base64Str to set
     */
    public void setBase64Str(String base64Str) {
        this.base64Str = base64Str;
    }
}
