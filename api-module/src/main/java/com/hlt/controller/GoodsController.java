package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.entity.GoodsEntity;
import com.hlt.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: houlintao
 * @Date:2020/7/9 下午4:47
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;


    @PostMapping("/create")
    public Object cresteGoods(){
        JSONObject jsonRequest = getJsonRequest();
        String name = jsonRequest.getString("name");
        String goods_sn = jsonRequest.getString("goods_sn");
        Integer brand_id = jsonRequest.getInteger("brand_id");

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setName(name);
        goodsEntity.setBrand_id(brand_id);
        goodsEntity.setGoods_sn(goods_sn);

        goodsService.save(goodsEntity);

        HashMap<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("msg","商品创建成功");
        result.put("data",goodsEntity);

        return toResponsSuccess(result);
    }
}
