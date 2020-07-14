package com.hlt.dao;

import com.hlt.entity.GoodsInOrderEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author: houlintao
 * @Date:2020/7/13 上午9:11
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface GoodsInOrderDao extends BaseDao<GoodsInOrderEntity>{
    @Override
    int save(GoodsInOrderEntity goodsInOrderEntity);
}
