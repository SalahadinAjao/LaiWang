package com.hlt.dao;

import com.hlt.entity.SysRegionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/10 下午4:39
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface SysRegionDao extends BaseDao<SysRegionEntity> {
    @Override
    List<SysRegionEntity> queryList(Map<String, Object> map);

    @Override
    SysRegionEntity queryObject(Object id);
}
