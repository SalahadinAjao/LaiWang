package com.hlt.utils;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: houlintao
 * @Date:2020/7/11 下午3:42
 * @email 437547058@qq.com
 * @Version 1.0
 * 字符串处理工具类
 */
public class StringUtils {

    public static final String EMPTY="";
    //"_(\\w)"匹配的就是 _w 或者 _A 这种由一个下划线和任意字母的字符串
    private static Pattern p = Pattern.compile("_(\\w)");

    /**
     *@date: 2020/7/12 上午10:50
     * @param object 待转对象
     * @param isCancel 是否去掉空格
     * @param pattern 数据类型,就是格式化日期对象的模式对象
     * 将对象转换为字符串
     */
    public static String objectToString(Object object, boolean isCancel,String pattern){
        //如果对象为空，直接返回一个空字符串
        if (object==null){
            return "";
        }else {
            //如果需要去掉空格
            if (isCancel){
               return object.toString().trim();
                //不需要去掉空格
            }else {
                //判断dataType中是否有文本,如果有
              if (StringUtils.hasText(pattern)){
                  //object是不是Timestamp类对象或者其子类对象
                  /**
                   * 在开发web应用中，针对不同的数据库对各自日期类型的需求，我们需要在程序中对日期类型做各种不同的转换。
                   * 若对应数据库数据是oracle的Date类型，即只需要年月日的，可以选择使用java.sql.Date类型；
                   * 若对应的是MSsqlserver数据库的DateTime类型，即需要年月日时分秒的，选择java.sql.Timestamp类型;
                   * Timestamp 可以精确到小数秒 一般存储的格式：2020-01-29 04:33:38.531
                   * Timestamp 可以获取当前时间，也可以把字符串装换成Timestamp类型。
                   */
                  if (object instanceof Timestamp){
                       return DateUtils.format((Timestamp) object,pattern);
                  }else if (object instanceof Date){
                      return DateUtils.format((Timestamp)object,pattern);
                  }
              }
            }
        }
        return object.toString();
    }

    /**
     * 下划线转驼峰
     */
    public static String underLineToCanel(String string){
        //把原字符串变小写
        string = string.toLowerCase();
        //获取matcher对象
        Matcher matcher = p.matcher(string);
        //声明一个StringBuffer用于保存数据
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()){
            /**
             * appendReplacement方法会把匹配到的内容替换为matcher.group(1).toUpperCase()，并且把从上次替换的位置
             * 到这次替换位置之间的字符串也拿到，然后，加上这次替换后的结果一起追加到StringBuffer里，
             * 假如这次替换是第一次替换，那就是只追加替换后的字符串了。
             */
            matcher.appendReplacement(buffer,matcher.group(1).toUpperCase());
        }
        //把最后一次匹配到内容之后的字符串追加到StringBuffer中
        matcher.appendTail(buffer);
        return buffer.toString();
    }
    /**
     * 判断字符串是否不为空
     */
    public boolean isNotEmpty(String string){
        if (string!=null && !string.trim().equals("")){
            return true;
        }
        return false;
    }
    /**
     * 判断对象或者对象数组中每个对象是否为空；
     * 判断字符序列长度是否为0；
     * 判断集合，Map是否为empty;
     */
    public static boolean isNullOrEmpty(Object object){
        if (object==null){
            return true;
        }
        //如果object是个字符序列
        if (object instanceof CharSequence){
            return ((CharSequence)object).length()==0;
        }
        //如果object是集合
        if (object instanceof Collection){
            return ((Collection)object).isEmpty();
        }
        //如果object是Map
        if (object instanceof Map){
            return ((Map)object).isEmpty();
        }
        //如果object是对象数组类或者其子类
        if (object instanceof Object[]){
            //需要强转
            Object[] objs = (Object[]) object;
            if (objs.length==0){
                return true;
            }
            boolean isEmpty = true;
            for (int i=0;i<objs.length;i++){
                //递归调用
                if (!isNullOrEmpty(objs[i])){
                    isEmpty=false;
                    break;
                }
            }
            return isEmpty;
        }
        return false;
    }

    //字符序列中是否有文本
    public static boolean hasText(CharSequence sequence){
        //若字符序列为空
        if (!hasLength(sequence)){
            return false;
        }else {
            int strLength = sequence.length();
            for (int i=0;i<strLength;i++){
                //遍历判断字符序列的每一位字符是否为空白字符：空格，tab键，换行符
                //如果都不是则返回true
                if (!Character.isWhitespace(sequence.charAt(i))){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 判断字符串中是否有文本，内部使用的是验证字符序列的方法
     */
    public static boolean hasText(String string){
        return hasText((CharSequence) string);
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }
}
