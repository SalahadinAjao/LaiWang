package com.hlt.dao;

import com.hlt.entity.OrderEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author: houlintao
 * @Date:2020/7/13 上午11:37
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface OrderDao extends BaseDao<OrderEntity> {

    @Override
    int save(OrderEntity orderEntity);

    @Override
    OrderEntity queryObject(Object id);

    @Override
    void update(OrderEntity entity);
}
