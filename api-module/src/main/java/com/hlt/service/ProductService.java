package com.hlt.service;

import com.hlt.entity.ProductEntity;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午10:46
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface ProductService {
    void save(ProductEntity entity);

    void update(ProductEntity entity);

    ProductEntity queryObject(Integer id);
}
