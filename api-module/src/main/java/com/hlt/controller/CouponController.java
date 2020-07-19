package com.hlt.controller;

import com.hlt.annotations.CurrentLoginUser;
import com.hlt.entity.CouponEntity;
import com.hlt.entity.UserEntity;
import com.hlt.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: houlintao
 * @Date:2020/7/19 下午5:03
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/coupon")
public class CouponController extends BaseController {
    @Autowired
    private CouponService couponService;

    /**
     * 根据user_id获取优惠券列表
     */
    @PostMapping("/list")
    public Object couponsListByUserId(@CurrentLoginUser UserEntity currentUser){
        HashMap param = new HashMap();
        param.put("user_id",currentUser.getUserId());

        List<CouponEntity> couponList = couponService.queryUserCoupons(param);
        return toResponsSuccess(couponList);
    }


}
