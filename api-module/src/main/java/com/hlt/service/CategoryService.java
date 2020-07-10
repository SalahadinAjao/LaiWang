package com.hlt.service;

import com.hlt.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/10 上午10:33
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface CategoryService {
    List<CategoryEntity> queryList(Map<String,Object> map);
    CategoryEntity queryObject(Integer id);
}
