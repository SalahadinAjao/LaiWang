package com.hlt.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: houlintao
 * @Date:2020/7/10 下午4:28
 * @email 437547058@qq.com
 * @Version 1.0
 * ApplicationContextAware 通过它,Spring容器会自动把上下文环境对象调用ApplicationContextAware
 * 接口中的setApplicationContext方法。
 * 我们在ApplicationContextAware的实现类中，就可以通过这个上下文环境对象得到Spring容器中的Bean。
 * 当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中
 * 的所有bean。
 * 换句话说，就是这个类可以直接获取spring配置文件中所有有引用到的bean对象。
 */

public class SpringContextUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       SpringContextUtils.applicationContext=applicationContext;
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    public static Class<? extends Object> getType(String name) {
        return applicationContext.getType(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        System.out.println("执行了SpringContextUtil的getBean方法" + requiredType);
        return applicationContext.getBean(requiredType);
    }
}
