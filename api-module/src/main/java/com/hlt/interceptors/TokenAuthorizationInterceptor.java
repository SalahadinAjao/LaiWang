package com.hlt.interceptors;

import com.hlt.annotations.SkipAuth;
import com.hlt.entity.TokenEntity;
import com.hlt.service.TokenService;
import com.hlt.utils.ApiRRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: houlintao
 * @Date:2020/7/8 上午8:04
 * @email 437547058@qq.com
 * @Version 1.0
 * Token验证拦截器，验证方法上面有没有 @SkipAuth 注解，如果有就跳过token验证直接执行，如果没有则需要
 * 从前端的请求对象中获取token
 */

@Component
public class TokenAuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenService tokenService;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    public static final String LOGIN_TOKEN_KEY = "X-WeiJiZhang-Token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("执行了拦截器的preHandle方法 "+"\n"+"request = " + request);

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,X-WeiJiZhang-Token,X-URL-PATH");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        SkipAuth skipAuth;
        /**
         * 判断处理器对象 handler 是否是 HandlerMethod 类的子类或者本身，如果是就说明 handler 是
         * 个Spring 框架定义的方法对象，需要校验此方法上是否有注解 @SkipAuth;
         */
        if (handler instanceof HandlerMethod){
            skipAuth =  ((HandlerMethod)handler).getMethodAnnotation(SkipAuth.class);
        }else {//如果不是，则说明handler是Object类的方法，不需要校验，直接返回true
            return true;
        }
        //如果此方法上有SkipAuth注解，则跳过验证，直接返回true
        if (skipAuth != null){
            return true;
        }
        //校验token，先从request中的header获取token
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        if (StringUtils.isBlank(token)){
            //如果header中内没有token，则从请求参数获取
            token = request.getParameter(LOGIN_TOKEN_KEY);
            System.out.println("本次请求客户端的token = " + token);
        }
        if (StringUtils.isBlank(token)){
            throw  new ApiRRException("账户未登录，请先登录",401);
        }
        //如果token不为null，则通过token属性查询到Token实体
        TokenEntity tokenEntity = tokenService.queryByToken(token);

        if (tokenEntity == null || tokenEntity.getExpireTime().getTime()<System.currentTimeMillis()){
            throw  new ApiRRException("token失效，请重新登录",402);
        }
        /**
         * 设置userId到request里，以LOGIN_USER_KEY 作为key，以对应的userId值为value，这样request对
         * 象中就有相关数据礼，在后续此request对象还要被消息转换器解析为特定控制器的参数对象，根
         * 据userId就可已获取用户信息。
         */
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());
        return true;
    }
}
