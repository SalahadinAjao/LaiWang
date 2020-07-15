package com.hlt.dao;

import com.hlt.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Override
    int queryTotal();

    @Override
    List<OrderEntity> queryList(Map<String, Object> map);
}
