package com.hlt.service.Impl;

import com.hlt.dao.GoodsInOrderDao;
import com.hlt.entity.GoodsInOrderEntity;
import com.hlt.service.GoodsInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/13 上午9:34
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class GoodsInOrderServiceImpl implements GoodsInOrderService {
    @Autowired
    private GoodsInOrderDao goodsInOrderDao;
    @Override
    public void save(GoodsInOrderEntity goodsInOrderEntity) {
        goodsInOrderDao.save(goodsInOrderEntity);
    }

    @Override
    public List<GoodsInOrderEntity> queryList(Map<String, Object> map) {
       return goodsInOrderDao.queryList(map);
    }
}
