package com.hlt.dao;

import com.hlt.entity.SpecificationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/17 上午10:39
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface SpecificationDao extends BaseDao<SpecificationEntity>{

    @Override
    int save(SpecificationEntity specificationEntity);

    @Override
    SpecificationEntity queryObject(Object id);

    @Override
    List<SpecificationEntity> queryList(Map<String, Object> map);
}
