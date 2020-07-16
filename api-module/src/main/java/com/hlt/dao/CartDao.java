package com.hlt.dao;

import com.hlt.entity.CartEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/16 下午5:52
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface CartDao extends BaseDao<CartEntity> {
    @Override
    int save(CartEntity cartEntity);

    @Override
    int update(CartEntity cartEntity);

    @Override
    CartEntity queryObject(Object id);

    @Override
    List<CartEntity> queryList(Long userId);

    @Override
    List<CartEntity> queryList(Map<String, Object> map);
}
