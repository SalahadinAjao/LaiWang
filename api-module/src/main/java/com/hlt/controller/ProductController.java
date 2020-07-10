package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.entity.ProductEntity;
import com.hlt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @Author: houlintao
 * @Date:2020/7/9 下午3:20
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;


    @PostMapping("/create")
    public Object createProduct(){
        JSONObject jsonRequest = getJsonRequest();

        Integer goods_id = jsonRequest.getInteger("goods_id");
        String goods_specification_ids = jsonRequest.getString("goods_specification_ids");
        String goods_sn = jsonRequest.getString("goods_sn");
        Integer goods_number = jsonRequest.getInteger("goods_number");
        BigDecimal market_price = jsonRequest.getBigDecimal("market_price");
        BigDecimal retail_price = jsonRequest.getBigDecimal("retail_price");
        String goods_name = jsonRequest.getString("goods_name");

        ProductEntity productEntity = new ProductEntity();

        productEntity.setGoods_id(goods_id);
        productEntity.setGoods_specification_ids(goods_specification_ids);
        productEntity.setGoods_sn(goods_sn);
        productEntity.setGoods_number(goods_number);
        productEntity.setMarket_price(market_price);
        productEntity.setRetail_price(retail_price);
        productEntity.setGoods_name(goods_name);

        productService.save(productEntity);

        return toResponsObject(200,"新建产品成功",productEntity);
    }

    @PostMapping("/update")
    public Object updateProductInfo(){
        JSONObject jsonRequest = getJsonRequest();
        Integer id = jsonRequest.getInteger("id");
        Integer goods_id = jsonRequest.getInteger("goods_id");
        String goods_specification_ids = jsonRequest.getString("goods_specification_ids");
        String goods_sn = jsonRequest.getString("goods_sn");
        Integer goods_number = jsonRequest.getInteger("goods_number");
        BigDecimal market_price = jsonRequest.getBigDecimal("market_price");
        BigDecimal retail_price = jsonRequest.getBigDecimal("retail_price");
        String goods_name = jsonRequest.getString("goods_name");
        String list_pic_url = jsonRequest.getString("list_pic_url");

        ProductEntity productEntity = productService.queryObject(id);
        productEntity.setGoods_id(goods_id);
        productEntity.setGoods_specification_ids(goods_specification_ids);
        productEntity.setGoods_sn(goods_sn);
        productEntity.setGoods_number(goods_number);
        productEntity.setMarket_price(market_price);
        productEntity.setRetail_price(retail_price);
        productEntity.setGoods_name(goods_name);
        productEntity.setList_pic_url(list_pic_url);

        productService.update(productEntity);

        HashMap<String, Object> returnObj = new HashMap<>();
        returnObj.put("code",200);
        returnObj.put("data",productEntity);
        returnObj.put("msg","产品更新成功");

        return toResponsSuccess(returnObj);
    }
}
