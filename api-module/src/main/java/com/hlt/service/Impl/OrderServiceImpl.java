package com.hlt.service.Impl;

import com.hlt.dao.OrderDao;
import com.hlt.entity.OrderEntity;
import com.hlt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/14 上午7:04
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Override
    public void save(OrderEntity entity) {
        orderDao.save(entity);
    }

    @Override
    public OrderEntity queryObject(Integer id) {
        return orderDao.queryObject(id);
    }

    @Override
    public int update(OrderEntity entity) {
       return orderDao.update(entity);
    }

    @Override
    public int queryTotal() {
        return orderDao.queryTotal();
    }

    @Override
    public List<OrderEntity> queryList(Map<String, Object> map) {
        return orderDao.queryList(map);
    }
}
