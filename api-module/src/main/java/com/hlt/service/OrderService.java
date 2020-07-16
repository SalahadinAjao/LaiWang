package com.hlt.service;

import com.hlt.entity.OrderEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/14 上午7:03
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface OrderService {

    void save(OrderEntity entity);

    OrderEntity queryObject(Integer id);

    int update(OrderEntity entity);

    int queryTotal();

    List<OrderEntity> queryList(Map<String,Object> map);
}
