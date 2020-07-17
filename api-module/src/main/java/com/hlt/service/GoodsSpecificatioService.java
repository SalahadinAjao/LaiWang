package com.hlt.service;

import com.hlt.entity.GoodsSpecificationEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午11:18
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface GoodsSpecificatioService {
    void save(GoodsSpecificationEntity goodsSpecificationEntity);

    List<GoodsSpecificationEntity> queryList(Map map);
}
