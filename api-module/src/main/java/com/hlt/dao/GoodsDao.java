package com.hlt.dao;

import com.hlt.entity.GoodsEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author: houlintao
 * @Date:2020/7/9 下午2:54
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface GoodsDao extends BaseDao<GoodsEntity> {
    @Override
    int save(GoodsEntity goodsEntity);

    @Override
    GoodsEntity queryObject(Object id);
}
