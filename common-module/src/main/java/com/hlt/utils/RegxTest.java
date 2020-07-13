package com.hlt.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: houlintao
 * @Date:2020/7/12 上午10:34
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class RegxTest {

    private static Pattern pattern = Pattern.compile("_(\\w)");

    public static String underLineToCamel(String string){
        string = string.toLowerCase();

        Matcher matcher = pattern.matcher(string);

        StringBuffer stringBuffer = new StringBuffer();

        while (matcher.find()){
            matcher.appendReplacement(stringBuffer,matcher.group(1).toUpperCase());
        }
          matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String str = "user_id,user_name,user_age,user_password";
        String toCamel = underLineToCamel(str);
        System.out.println(toCamel);
    }
}
