package com.hlt.utils;

import java.util.Random;

/**
 * @Author: houlintao
 * @Date:2020/7/7 下午5:20
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class CharUtils {

    /**
     * 获取数字组成的随机字符串
     */
    public static String getRandomNumStr(Integer num){
        String base = "0123456789abcdefgh{ijklm}n][opqrs@tuvwxyzABC?/~!DEF,GH<I>JKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
