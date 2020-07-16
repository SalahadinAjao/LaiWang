package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.CurrentLoginUser;
import com.hlt.annotations.SkipAuth;
import com.hlt.controller.BaseController;
import com.hlt.entity.CartEntity;
import com.hlt.entity.GoodsEntity;
import com.hlt.entity.ProductEntity;
import com.hlt.entity.UserEntity;
import com.hlt.service.CartService;
import com.hlt.service.GoodsService;
import com.hlt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Author: houlintao
 * @Date:2020/7/16 下午6:20
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProductService productService;

    //@SkipAuth
    @PostMapping("/save")
    public Object save(@CurrentLoginUser UserEntity loginUser){
        JSONObject jsonRequest = getJsonRequest();
        String session_id = jsonRequest.getString("sessionId");
        Integer goods_id = jsonRequest.getInteger("goodsId");
        Integer number = jsonRequest.getInteger("number");
        Integer checked = jsonRequest.getInteger("checked");
        String list_pic_url = jsonRequest.getString("list_pic_url");

        GoodsEntity goodsEntity = goodsService.queryObject(goods_id);
        if (goodsEntity == null){
            return toResponsFail("商品已下架");
        }
        String goods_sn = goodsEntity.getGoods_sn();
        String goods_name = goodsEntity.getName();
        Integer product_id = goodsEntity.getProduct_id();
        BigDecimal market_price = goodsEntity.getMarket_price();
        BigDecimal retail_price = goodsEntity.getRetail_price();

        ProductEntity productEntity = productService.queryObject(product_id);
        String goods_specification_ids = productEntity.getGoods_specification_ids();

        CartEntity cartEntity = new CartEntity();
        cartEntity.setUser_id(loginUser.getUserId());
        cartEntity.setSession_id(session_id);
        cartEntity.setChecked(checked);
        cartEntity.setList_pic_url(list_pic_url);
        cartEntity.setGoods_id(goods_id);
        cartEntity.setNumber(number);
        cartEntity.setGoods_name(goods_name);
        cartEntity.setGoods_sn(goods_sn);
        cartEntity.setProduct_id(product_id);
        cartEntity.setMarket_price(market_price);
        cartEntity.setRetail_price(retail_price);

        cartService.save(cartEntity);

        return toResponsObject(200,"购物车保存成功",cartEntity);
    }
}
