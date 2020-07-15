package com.hlt.dao;

import com.hlt.entity.GoodsInOrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    List<GoodsInOrderEntity> queryList(@Param("order_id") Integer order_id);

    @Override
    List<GoodsInOrderEntity> queryList(Map<String, Object> map);
}
