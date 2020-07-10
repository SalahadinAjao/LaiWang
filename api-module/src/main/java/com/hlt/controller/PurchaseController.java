package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.CurrentLoginUser;
import com.hlt.cache.J2CacheUtil;
import com.hlt.entity.GoodsPurchasedEntity;
import com.hlt.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: houlintao
 * @Date:2020/7/10 上午8:19
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController extends BaseController {
    /**
     * 立即购买（与添加进购物车区别）
     */
    @PostMapping("/buynow")
    public Object buyNow(@CurrentLoginUser UserEntity loginUser){
        JSONObject jsonRequest = getJsonRequest();
        Integer goods_id = jsonRequest.getInteger("goods_id");
        Integer productId = jsonRequest.getInteger("productId");
        Integer number = jsonRequest.getInteger("number");
        String name = jsonRequest.getString("name");

        GoodsPurchasedEntity entity = new GoodsPurchasedEntity();
        entity.setGoodsId(goods_id);
        entity.setProductId(productId);
        entity.setNumber(number);
        entity.setName(name);
        /**
         * 与加入购物车不同，在这里使用缓存
         * 当用户确认后如果顺利支付则此订单信息会被存储进数据库；
         * 如果用户没有支付，则此订单信息会被存进缓存，缓存时间12小时；
         */
        //支付逻辑，先占坑

        J2CacheUtil.put(J2CacheUtil.LAIWANG_CACHE_NAME,"goods"+loginUser.getUserId()+"",entity);

        return toResponsSuccess("请尽快支付");
    }
}
