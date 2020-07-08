package com.hlt.validators;

import com.hlt.utils.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: houlintao
 * @Date:2020/7/8 下午5:53
 * @email 437547058@qq.com
 * @Version 1.0
 * 数据校验
 */
public abstract class Assert {

    public static void isBlank(String string,String msg){
        if (StringUtils.isBlank(string)){
            throw new RRException(msg);
        }
    }

    public static void isNull(Object obj ,String msg){
        if (obj==null){
            throw new RRException(msg);
        }
    }
}
