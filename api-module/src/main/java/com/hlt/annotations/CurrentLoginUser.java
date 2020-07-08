package com.hlt.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: houlintao
 * @Date:2020/7/8 上午9:18
 * @email 437547058@qq.com
 * @Version 1.0
 * 一个标志当前登录用户状态的注解，在对应的参数解析器扫描到此注解时会自动的注入当前用户
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentLoginUser {
}
