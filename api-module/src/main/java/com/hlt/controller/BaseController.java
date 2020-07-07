package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/7 上午11:19
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class BaseController {
    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    public Map<String, Object> toResponsObject(int requestCode, String msg, Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", requestCode);
        obj.put("errmsg", msg);
        if (data != null)
            obj.put("data", data);
        return obj;
    }

    public Map<String, Object> toResponsSuccess(Object data) {
        Map<String, Object> rp = toResponsObject(0, "执行成功", data);
        logger.info("response:" + rp);
        return rp;
    }

    public Map<String, Object> toResponsMsgSuccess(String msg) {
        return toResponsObject(0, msg, "");
    }

    public Map<String, Object> toResponsSuccessForSelect(Object data) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("list", data);
        return toResponsObject(0, "执行成功", result);
    }

    public Map<String, Object> toResponsFail(String msg) {
        return toResponsObject(1, msg, null);
    }

    public String getClientIp() {
        String xff = request.getHeader("X-Real-IP");
        if(xff!=null) {
            return xff;
        }
        xff = request.getHeader("x-forwarded-for");
        if (xff == null) {

            return "8.8.8.8";
        }
        return xff;
    }

    public JSONObject getJsonRequest() {
        JSONObject result = null;
        /**
         * 声明一个可变stringBuilder用于保存每次读取的数据
         */
        StringBuilder sb = new StringBuilder();
        /**不论是request对象还是respond对象，它们都是流对象，底层都是socket有关的数据交换*/
        try (BufferedReader reader = request.getReader();) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
            //将sb中的字符转换为string
            result = JSONObject.parseObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
