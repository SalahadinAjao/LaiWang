package com.hlt.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: houlintao
 * @Date:2020/7/7 上午11:24
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class MD5 {
    public String EncodeByMd5(String oldStr) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();

        String newStr = base64Encoder.encode(md5.digest(oldStr.getBytes("utf-8")));
        return newStr;
    }
}
