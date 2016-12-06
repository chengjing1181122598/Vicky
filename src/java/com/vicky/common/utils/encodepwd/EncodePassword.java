/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vicky.common.utils.encodepwd;

/**
 * BCrypt加密算法封装
 *
 * @author Vicky
 */
public final class EncodePassword {

    //默认盐
    public static final String DEFAULT_SALT = "cn.livicky.www";

    /**
     * 返回加密后的hash值
     *
     * @author Vicky
     * @param password 密码
     * @param salt 自定义的盐
     * @return 加密后的hash值
     */
    public static String encodePassword(String password, String salt) {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt() + salt);
        return passwordHash;
    }

    /**
     * 返回加密后的hash值
     *
     * @author Vicky
     * @param password 密码
     * @return 加密后的hash值
     */
    public static String encodePassword(String password) {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt() + EncodePassword.DEFAULT_SALT);
        return passwordHash;
    }

    /**
     * 检查密码是否正确
     *
     * @author Vicky
     * @param password 密码
     * @param passwordHash 密码hash值
     * @return boolean类型
     */
    public static boolean checkPassword(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }
}
