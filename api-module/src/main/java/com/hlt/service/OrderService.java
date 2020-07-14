package com.hlt.service;

import com.hlt.entity.OrderEntity;

/**
 * @Author: houlintao
 * @Date:2020/7/14 上午7:03
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface OrderService {

    void save(OrderEntity entity);

    OrderEntity queryObject(Integer id);

    void update(OrderEntity entity);
}
