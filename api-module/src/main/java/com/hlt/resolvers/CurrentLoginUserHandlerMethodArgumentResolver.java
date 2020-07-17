package com.hlt.resolvers;

import com.hlt.annotations.CurrentLoginUser;
import com.hlt.entity.UserEntity;
import com.hlt.interceptors.TokenAuthorizationInterceptor;
import com.hlt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Author: houlintao
 * @Date:2020/7/8 上午9:12
 * @email 437547058@qq.com
 * @Version 1.0
 * 处理器方法参数解析器，原理同拦截器差不多，都是拦截处理器(处理器方法)，此类对象的作用是通过拦截处理器方法参数上的
 * 注解@CurrentLoginUser注解将当前请求本方法的用户传进方法
 */

@Component
public class CurrentLoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserService userService;

    public void setUserService(UserService service){
        this.userService = service;
    }

    /**
     * 首先会先执行这个函数，在此函数判断：
     * 1.此方法参数是不是 UserEntity 类型；
     * 2.此方法参数上是不是有 CurrentLoginUser 注解；
     * 只有以上两个条件都满足才会返回true，才会执行下一个方法。
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return  methodParameter.getParameterType().isAssignableFrom(UserEntity.class)
                && methodParameter.hasParameterAnnotation(CurrentLoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        /**
         *  LOGIN_USER_KEY 只是一个保存在 TokenAuthorizationInterceptor 类中的静态常量，用以标志用户请求对象的信息,在每次用户请求后端的时候会被
         *  TokenAuthorizationInterceptor 拦截器对象拦截，拦截器对象会将当前请求用户的 userId 设置进当前的 request 对象中并以 LOGIN_USER_KEY为key，
         *  以 userId 值为 value，然后再由此 CurrentLoginUserHandlerMethodArgumentResolver 解析器解析，获取对应的参数并最终注入userEntity对象；
         *  此方法的作用是在一个request范围内获取以 LOGIN_USER_KEY 为属性名的属性值，这个值是被保存在request对象中的。
         */
        Object attribute = nativeWebRequest.getAttribute(TokenAuthorizationInterceptor.LOGIN_USER_KEY, RequestAttributes.SCOPE_REQUEST);

        if (attribute == null){
            return null;
        }
        UserEntity userEntity = userService.queryObject((Long) attribute);
        return userEntity;
    }
}
