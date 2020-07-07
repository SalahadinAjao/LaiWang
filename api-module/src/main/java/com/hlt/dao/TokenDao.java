package com.hlt.dao;

import com.hlt.entity.TokenEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: houlintao
 * @Date:2020/7/7 下午5:14
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface TokenDao extends BaseDao<TokenEntity> {
    TokenEntity queryByUserId(long userId);

    TokenEntity queryByToken(@Param("token") String token);
}
