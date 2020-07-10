package com.hlt.dao;

import com.hlt.entity.ProductEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午10:45
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface ProductDao extends BaseDao<ProductEntity> {
    @Override
    void update(ProductEntity productEntity);

    @Override
    ProductEntity queryObject(Object id);
}
