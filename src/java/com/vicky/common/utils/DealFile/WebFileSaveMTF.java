/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.DealFile;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 线程保存文件
 *
 * @author Vicky
 */
public class WebFileSaveMTF implements Runnable {

    /**
     * 获取日志Logger,为org.slf4j.Logger
     *
     * @return org.slf4j.Logger
     */
    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    private MultipartFile multipartFile;
    private String path;
    private String filename;

    public WebFileSaveMTF(MultipartFile multipartFile, String path, String filename) {
        this.multipartFile = multipartFile;
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
            this.multipartFile.transferTo(targetFile);
//            //以流的方式写入文件
//            InputStream inputStream = this.multipartFile.getInputStream();
//            int bufferSize;
//            if (this.multipartFile.getSize() > Integer.MAX_VALUE) {
//                bufferSize = Integer.MAX_VALUE;
//            } else {
//                bufferSize = (int) this.multipartFile.getSize();
//            }
//
//            FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
//
//            BufferedInputStream bis = new BufferedInputStream(inputStream, bufferSize);
//            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream, bufferSize);
//            int r;
//            while ((r = bis.read()) != -1) {
//                bos.write(r);
//            }
//            bis.close();
//            bos.close();
//
//            inputStream.close();
//            fileOutputStream.close();
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
    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    /**
     * @param multipartFile the multipartFile to set
     */
    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
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
