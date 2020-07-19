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

    //查询用户可用的最大优惠券
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
