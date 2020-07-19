package com.hlt.service.Impl;

import com.hlt.entity.UserCouponEntity;
import com.hlt.service.UserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/19 下午4:41
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class UserCouponServiceImpl implements UserCouponService {
    @Autowired
    private UserCouponService userCouponService;

    @Override
    public UserCouponEntity queryByCouponNumber(String coupon_number) {
        return userCouponService.queryByCouponNumber(coupon_number);
    }

    @Override
    public List<UserCouponEntity> queryList(Map<String, Object> map) {
        return userCouponService.queryList(map);
    }

    @Override
    public int save(UserCouponEntity userCouponEntity) {
        return userCouponService.save(userCouponEntity);
    }

    @Override
    public UserCouponEntity queryObject(Object id) {
        return userCouponService.queryObject(id);
    }
}
