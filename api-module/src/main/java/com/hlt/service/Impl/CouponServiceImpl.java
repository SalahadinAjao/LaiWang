package com.hlt.service.Impl;

import com.hlt.entity.CouponEntity;
import com.hlt.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/19 下午4:47
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponService couponService;


    @Override
    public int save(CouponEntity couponEntity) {
        return couponService.save(couponEntity);
    }

    @Override
    public int update(CouponEntity couponEntity) {
        return couponService.update(couponEntity);
    }

    @Override
    public CouponEntity queryObject(Object id) {
        return couponService.queryObject(id);
    }

    @Override
    public List<CouponEntity> queryList(Map<String, Object> map) {
        return couponService.queryList(map);
    }

    @Override
    public int queryTotal() {
        return couponService.queryTotal();
    }

    @Override
    public int deleteBatch(Object[] id) {
        return couponService.deleteBatch(id);
    }

    @Override
    public int delete(Object id) {
        return couponService.delete(id);
    }

    /**
     * 这个函数会遍历每一个符合条件的优惠券对象，并根据具体情况将过期的对象设置为过期，未过期的
     * 对象设置为未过期，并将设置后的对象返回前端。
     */
    @Override
    public List<CouponEntity> queryUserCoupons(Map<String, Object> params) {
        List<CouponEntity> entityList = couponService.queryUserCoupons(params);

        for (CouponEntity couponEntity:entityList){
            //如果优惠券是可用状态
            if (couponEntity.getCoupon_status()==1){
                //如果优惠券的最后使用期限早于此刻
                if (couponEntity.getUse_end_date().before(new Date())){
                    //优惠券以过期，设置过期
                    couponEntity.setCoupon_status(3);
                    couponService.update(couponEntity);
                }
            }
            //如果优惠券为不可用状态
            if (couponEntity.getCoupon_status()==3){
                //检查优惠券是否为未过期
                if (couponEntity.getUse_end_date().after(new Date())){
                    //设置优惠券未过期
                    couponEntity.setCoupon_status(1);
                    couponService.update(couponEntity);
                }
            }
        }
        return entityList;
    }

    @Override
    public CouponEntity queryMaxUserEnableCoupon(Map<String, Object> params) {
        return couponService.queryMaxUserEnableCoupon(params);
    }

    @Override
    public List<CouponEntity> queryCouponList(Map<String, Object> params) {
        return couponService.queryCouponList(params);
    }

    @Override
    public CouponEntity getUserCoupon(Integer userCouponId) {
        return couponService.getUserCoupon(userCouponId);
    }
}
