package com.hlt.service;

import com.hlt.entity.TokenEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/7 下午5:17
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface TokenService {
    void save(TokenEntity entity);

    Map<String,Object> createToken(long userId);

    TokenEntity queryByUserId(long userId);

    void update(TokenEntity entity);

    TokenEntity queryByToken(@Param("token") String token);
}
