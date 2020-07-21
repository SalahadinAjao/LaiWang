package com.hlt.service;

import com.hlt.entity.CouponEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/19 下午4:38
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface CouponService {

    int save(CouponEntity couponEntity);

    int update(CouponEntity couponEntity);

    CouponEntity queryObject(Object id);

    List<CouponEntity> queryList(Map<String, Object> map);

    int queryTotal();

    int deleteBatch(Object[] id);

    int delete(Object id);

    List<CouponEntity> queryUserCoupons(Map<String, Object> params);

    /**
     * 根据 send_type 查询用户可以领取的最大优惠券，它返回的是一个通用的 CouponEntity 对象，这个对象表示的是新用户
     * 能够领取的最大金额的优惠券信息，用户领取的优惠券对象UserCouponEntity通过此 CouponEntity 的数据配置自身 。
     */
    CouponEntity queryMaxUserEnableCoupon(Map<String, Object> params);
    //根据传入的参数，查询用户优惠券列表
    List<CouponEntity> queryCouponList(Map<String, Object> params);
    /**
     * 根据用户优惠券的id查询通用的优惠券信息:
     * 将 UserCouponEntity 查询出来的 user_id, coupon_number和coupon_status数据
     * 覆盖从 CouponEntity 查询出来的值.
     */
    CouponEntity getUserCoupon(Integer userCouponId);
}
