package com.hlt.service.Impl;

import com.hlt.dao.GoodsDao;
import com.hlt.entity.GoodsEntity;
import com.hlt.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: houlintao
 * @Date:2020/7/9 下午2:55
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Override
    public void save(GoodsEntity goodsEntity) {
        goodsDao.save(goodsEntity);
    }

    @Override
    public GoodsEntity queryObject(Integer goodsId) {
        return goodsDao.queryObject(goodsId);
    }
}
