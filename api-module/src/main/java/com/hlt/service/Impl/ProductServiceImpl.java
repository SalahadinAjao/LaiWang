package com.hlt.service.Impl;

import com.hlt.dao.ProductDao;
import com.hlt.entity.ProductEntity;
import com.hlt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午10:48
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public void save(ProductEntity entity) {
         productDao.save(entity);
    }
}
