package com.hlt.service;

import com.hlt.entity.CartEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/16 下午6:19
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface CartService {

    void save(CartEntity cartEntity);

    int update(CartEntity cartEntity);

    List<CartEntity> queryList(Map map);
}
