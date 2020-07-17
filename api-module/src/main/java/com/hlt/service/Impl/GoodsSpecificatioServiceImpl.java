package com.hlt.service.Impl;

import com.hlt.dao.GoodsSpecificationDao;
import com.hlt.entity.GoodsSpecificationEntity;
import com.hlt.service.GoodsSpecificatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午11:19
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public class GoodsSpecificatioServiceImpl implements GoodsSpecificatioService {

    @Autowired
    private GoodsSpecificationDao goodsSpecificationDao;

    @Override
    public void save(GoodsSpecificationEntity goodsSpecificationEntity) {
        goodsSpecificationDao.save(goodsSpecificationEntity);
    }

    @Override
    public List<GoodsSpecificationEntity> queryList(Map map) {
        return goodsSpecificationDao.queryList(map);
    }
}
