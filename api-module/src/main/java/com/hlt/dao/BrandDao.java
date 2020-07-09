package com.hlt.dao;

import com.hlt.entity.BrandEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午7:24
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface BrandDao extends BaseDao<BrandEntity> {
    @Override
    int save(BrandEntity brandEntity);

    @Override
    List<BrandEntity> queryList(Map<String, Object> map);

    @Override
    int queryTotal(Map<String, Object> map);

    @Override
    BrandEntity queryObject(Object id);
}
