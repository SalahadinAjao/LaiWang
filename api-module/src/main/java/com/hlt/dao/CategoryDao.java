package com.hlt.dao;

import com.hlt.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/10 上午10:29
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface CategoryDao extends BaseDao<CategoryEntity> {
    @Override
    List<CategoryEntity> queryList(Map<String, Object> map);

    @Override
    CategoryEntity queryObject(Object id);
}
