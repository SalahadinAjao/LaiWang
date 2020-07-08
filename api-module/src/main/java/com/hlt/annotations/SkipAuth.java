package com.hlt.annotations;

import java.lang.annotation.*;

/**
 * @Author: houlintao
 * @Date:2020/7/8 上午8:06
 * @email 437547058@qq.com
 * @Version 1.0
 *  方法注解，使用在特定方法上表示此方法跳过token的验证授权
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SkipAuth {
}
