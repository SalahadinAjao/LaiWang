package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.CurrentLoginUser;
import com.hlt.cache.J2CacheUtil;
import com.hlt.entity.*;
import com.hlt.service.CartService;
import com.hlt.service.CouponService;
import com.hlt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

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

    @PostMapping("/enabledcoupon")
    public Object queryCouponListByGoodsTotalPrice(@RequestParam(defaultValue = "cart") String type, @CurrentLoginUser UserEntity currUser){

        BigDecimal goodsTotalPrice = new BigDecimal(0.00);

        HashMap param = new HashMap();

        //如果是购物车结算，那么就需要计算多种商品
        if (type == "cart"){
            param.put("user_id",currUser.getUserId());
            //根据 user_id 获取所有购物车
            List<CartEntity> cartList = cartService.queryList(param);
            //遍历购物车列表
            for (CartEntity cart :cartList){
                if (cart.getChecked()==1 && cart.getChecked() != null){
                    goodsTotalPrice = goodsTotalPrice.add(cart.getRetail_price().multiply(new BigDecimal(cart.getNumber())));
                }
            }
        }else {//如果不是购物车结算，则只需要计算当前一个商品，需要从缓存中获取缓存的数据
            //购买的商品
            GoodsPurchasedEntity goodsPurchased = (GoodsPurchasedEntity) J2CacheUtil.get(J2CacheUtil.LAIWANG_CACHE_NAME,"goods"+currUser.getUserId()+"");
            //通过产品实体获取价格
            ProductEntity product = productService.queryObject(goodsPurchased.getProductId());
            goodsTotalPrice = product.getRetail_price().multiply(new BigDecimal(goodsPurchased.getNumber()));
        }
        Map couponParam = new HashMap();
        couponParam.put("user_id",currUser.getUserId());
        //优惠券必须是可用状态
        couponParam.put("coupon_status",1);

        List<CouponEntity> couponList = couponService.queryUserCoupons(couponParam);
        //可用的优惠券
        ArrayList<CouponEntity> avaibleCoupons = new ArrayList<>();
        //不可用的优惠券
        ArrayList<CouponEntity> unAvaibleCoupons = new ArrayList<>();

        //遍历
        for (CouponEntity couponEntity:couponList){
            if (goodsTotalPrice.compareTo(couponEntity.getMin_goods_amount())>=0){
                 couponEntity.setEnabled(1);
                 avaibleCoupons.add(couponEntity);
            }else {
                couponEntity.setEnabled(0);
                unAvaibleCoupons.add(couponEntity);
            }
        }
        avaibleCoupons.addAll(unAvaibleCoupons);
        return toResponsSuccess(avaibleCoupons);
    }

    /**
     * 新用户填写手机号领取优惠券
     */
    @PostMapping("/couponbycellphone")
    public Object getCouponByNewCellPhone(@CurrentLoginUser UserEntity currUser){
        JSONObject jsonRequest = getJsonRequest();
        String phone = jsonRequest.getString("phone");
        Integer smsCode = jsonRequest.getInteger("smsCode");
    return null;

    }

}
