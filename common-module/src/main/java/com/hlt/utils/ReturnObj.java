package com.hlt.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/8 下午6:06
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class ReturnObj extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;

    public ReturnObj() {
        put("code", 0);
    }

    public static ReturnObj error() {
        return error(500, "未知错误");
    }

    public static ReturnObj error(String msg) {
        return error(500, msg);
    }

    public static ReturnObj error(int code, String msg) {
        ReturnObj r = new ReturnObj();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ReturnObj ok(String msg) {
        ReturnObj r = new ReturnObj();
        r.put("msg", msg);
        return r;
    }

    public static ReturnObj ok(Map<String, Object> map) {
        ReturnObj r = new ReturnObj();
        r.putAll(map);
        return r;
    }

    public static ReturnObj ok() {
        return new ReturnObj();
    }

    public ReturnObj put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
