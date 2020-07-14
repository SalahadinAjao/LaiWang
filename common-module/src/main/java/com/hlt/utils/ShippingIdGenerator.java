package com.hlt.utils;

import java.util.Random;

/**
 * @Author: houlintao
 * @Date:2020/7/14 上午7:39
 * @email 437547058@qq.com
 * @Version 1.0
 *  模拟运单号生成器
 */
public class ShippingIdGenerator {

    public static String generateShippingId(){
        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i=0;i<13;i++){
            int anInt = random.nextInt(base.length());
            stringBuffer.append(base.charAt(anInt));
        }
        return stringBuffer.toString();
    }
}
