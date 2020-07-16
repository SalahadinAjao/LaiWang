package com.hlt.service.Impl;

import com.hlt.dao.CartDao;
import com.hlt.entity.CartEntity;
import com.hlt.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: houlintao
 * @Date:2020/7/16 下午6:19
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Override
    public void save(CartEntity cartEntity) {
       cartDao.save(cartEntity);
    }

    @Override
    public int update(CartEntity cartEntity) {
       return cartDao.update(cartEntity);
    }
}
