package com.hlt.dao;

import com.hlt.entity.GoodsSpecificationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午11:10
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface GoodsSpecificationDao extends BaseDao<GoodsSpecificationEntity>{
    @Override
    int save(GoodsSpecificationEntity goodsSpecificationEntity);

    @Override
    GoodsSpecificationEntity queryObject(Object id);

    @Override
    List<GoodsSpecificationEntity> queryList(Map<String, Object> map);
}
