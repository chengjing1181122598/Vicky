/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.DealFile;

import com.vicky.common.utils.TreadPool.ThreadPool;
import java.io.File;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

/**
 * Web文件工具类,可保存和删除文件
 *
 * @author Vicky
 */
public class WebFileUtils {

    /**
     * 多线程删除文件
     * <p>
     * @param absolutePath 文件绝对路径
     */
    public static void deleteFile(String absolutePath) {
        File file = new File(absolutePath);
        ThreadPool.FILE_THREADPOOL.execute(new FileDelete(file));
    }

    /**
     * 保存web文件,文件经过UUID重命名以及哈希打散文件夹存储, 如果输入的用户名参数不变,则该用户的所有文件都在一个文件夹下
     * <br>
     * 则该用户的所有文件都在一个文件夹下, 由于只返回了绝对路径,保存后的文件只能用流的方式访问
     *
     * @param multipartFile MultipartFile类型
     * @param absoluteUrl 绝对路径目录
     * @param username 用户名,输入这个参数可以让文件清晰容量梳理
     * @return 文件保存后的绝对路径
     */
    public static String savePrivate(MultipartFile multipartFile, String absoluteUrl, String username) {
        String filename = multipartFile.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));

        //文件UUID重命名
        filename = UUID.randomUUID().toString() + suffix;
        //文件夹哈希打散存储
        int hash = (username + "www.livicky.cn").hashCode();
        String hashStr = Integer.toHexString(hash);//转成十六进制（长度为8） 每一位生成一个文件夹（每一级最多16个目录）
        char[] hss = hashStr.toCharArray();//转为char型数组
        for (char c : hss) {
            absoluteUrl = absoluteUrl + "/" + c;
        }
        ThreadPool.FILE_THREADPOOL.execute(new WebFileSave(multipartFile, absoluteUrl + "/" + username, filename));
        absoluteUrl = absoluteUrl + "/" + username + "/" + filename;
        return absoluteUrl;
    }

    /**
     * 保存web文件,文件经过UUID重命名以及哈希打散文件夹存储, 如果输入的用户名参数不变,
     * <br>
     * 则该用户的所有文件都在一个文件夹下, 由于返回了相对路径,直接在html元素"src"填写即可访问
     *
     * @param multipartFile MultipartFile类型
     * @param webRootPath web根目录,即浏览器主机url后面的第一个,适用与图片,视频,音乐等文件的"src"的填写
     * @param directory 保存的目录
     * @param username 用户名,输入这个参数可以让文件清晰容量梳理
     * @return 字符串数组,其中String[0]为绝对路径,String[1]为相对于web根目录的相对路径
     */
    public static String[] savePublicFileAtLocalServer(MultipartFile multipartFile,
            String webRootPath, String directory, String username) {
        //获得项目运行的路径
        String path = WebFileUtils.class.getClassLoader().getResource("").getPath();
        path = path.replace("WEB-INF/classes/", "") + directory;
        if (webRootPath.charAt(0) != '/') {
            webRootPath = "/" + webRootPath;
        }
        if (directory.charAt(0) != '/') {
            directory = "/" + directory;
        }
        String relativeUrl = webRootPath + directory;
        String filename = multipartFile.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));

        //文件UUID重命名
        filename = UUID.randomUUID().toString() + suffix;
        //文件夹哈希打散存储
        int hash = (username + "www.livicky.cn").hashCode();
        String hashStr = Integer.toHexString(hash);//转成十六进制（长度为8） 每一位生成一个文件夹（每一级最多16个目录）
        char[] hss = hashStr.toCharArray();//转为char型数组
        for (char c : hss) {
            path = path + "/" + c;
            relativeUrl = relativeUrl + "/" + c;
        }
        relativeUrl = relativeUrl + "/" + username + "/" + filename;
        path = path + "/" + username;
        String absoluteUrl = path + "/" + filename;
        ThreadPool.FILE_THREADPOOL.execute(new WebFileSave(multipartFile, path, filename));
        return new String[]{absoluteUrl, relativeUrl};
    }

    /**
     * 保存web文件,文件经过UUID重命名以及哈希打散文件夹存储, 如果输入的用户名参数不变,
     * <br>
     * 则该用户的所有文件都在一个文件夹下, 由于返回了相对路径,直接在html元素"src"填写即可访问
     *
     * @param multipartFile MultipartFile类型
     * @param otherServerPath 其他服务器绝对根路径,不包括项目根路径,即文件路径
     * @param otherServerUrl 其他服务器url,即主机地址和端口url,不包括项目根路径
     * @param webRootPath web根目录,即浏览器主机url后面的第一个,适用与图片,视频,音乐等文件的"src"的填写
     * @param directory 保存的目录
     * @param username 用户名,输入这个参数可以让文件清晰容量梳理
     * @return 字符串数组,其中String[0]为文件绝对路径,String[1]为其他服务器的路径
     */
    public static String[] savePublicFileAtOtherServer(MultipartFile multipartFile, String otherServerPath,
            String otherServerUrl, String webRootPath, String directory, String username) {
        if (webRootPath.charAt(0) != '/') {
            webRootPath = "/" + webRootPath;
        }
        if (directory.charAt(0) != '/') {
            directory = "/" + directory;
        }
        String path = otherServerPath + webRootPath + directory;
        String url = webRootPath + directory;
        String filename = multipartFile.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));

        //文件UUID重命名
        filename = UUID.randomUUID().toString() + suffix;
        //文件夹哈希打散存储
        int hash = (username + "www.livicky.cn").hashCode();
        String hashStr = Integer.toHexString(hash);//转成十六进制（长度为8） 每一位生成一个文件夹（每一级最多16个目录）
        char[] hss = hashStr.toCharArray();//转为char型数组
        for (char c : hss) {
            path = path + "/" + c;
            url = url + "/" + c;
        }
        url = url + "/" + username + "/" + filename;
        url = otherServerUrl + url;
        path = path + "/" + username;
        ThreadPool.FILE_THREADPOOL.execute(new WebFileSave(multipartFile, path, filename));
        path = path + "/" + filename;
        return new String[]{path, url};
    }

}
