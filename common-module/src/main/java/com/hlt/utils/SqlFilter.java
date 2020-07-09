package com.hlt.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午8:48
 * @email 437547058@qq.com
 * @Version 1.0
 * SQL参数过滤器，过滤sql语句的拼接参数，防止sql注入
 */
public class SqlFilter {

    public static String SqlInject(String string){
        if (StringUtils.isBlank(string)){
            return null;
        }
        //过滤'字符
        string = StringUtils.replace(string,"'","");
        //过滤\'字符
        string = StringUtils.replace(string,"\"","");
        //过滤;
        string = StringUtils.replace(string,";","");
        //过滤\\
        string = StringUtils.replace(string,"\\","");
        //转转成小写
        string = string.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete",
                "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (string.indexOf(keyword) != -1) {
                throw new RRException("包含非法字符");
            }
        }
        return string;
    }
}
