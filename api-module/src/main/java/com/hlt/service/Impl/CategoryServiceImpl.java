package com.hlt.service.Impl;

import com.hlt.dao.CategoryDao;
import com.hlt.entity.CategoryEntity;
import com.hlt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/10 上午10:34
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<CategoryEntity> queryList(Map<String, Object> map) {
        return categoryDao.queryList(map);
    }

    @Override
    public CategoryEntity queryObject(Integer id) {
        return categoryDao.queryObject(id);
    }
}
