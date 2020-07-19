package com.hlt.service;

import com.hlt.entity.UserCouponEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/19 下午4:39
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface UserCouponService {
    UserCouponEntity queryByCouponNumber(@Param("coupon_number")String coupon_number);

    List<UserCouponEntity> queryList(Map<String, Object> map);

    int save(UserCouponEntity userCouponEntity);

    UserCouponEntity queryObject(Object id);
}
