package com.hlt.service;

import com.hlt.entity.GoodsInOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/13 上午9:33
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface GoodsInOrderService {

    void save(GoodsInOrderEntity goodsInOrderEntity);

    List<GoodsInOrderEntity> queryList(Map<String,Object> map);
}
