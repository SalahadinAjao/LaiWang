package com.hlt.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/15 下午12:23
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface ShippingBirdService {

    List getOrderTracesByJson(String expCode, String expNo) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    String MD5(String str, String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    String base64(String str, String charset) throws UnsupportedEncodingException;

    String urlEncoder(String str, String charset) throws UnsupportedEncodingException;

    String encrypt(String content, String keyValue, String charset) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    String sendPost(String url, Map<String, String> params);


}
