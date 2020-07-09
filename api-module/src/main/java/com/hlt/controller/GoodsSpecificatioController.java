package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.entity.GoodsSpecificationEntity;
import com.hlt.service.GoodsSpecificatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午11:20
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/goodsSpecification")
public class GoodsSpecificatioController extends BaseController{
    @Autowired
    private GoodsSpecificatioService goodsSpecificatioService;

    @PostMapping("/create")
    public Object createSpec(){
        JSONObject jsonRequest = getJsonRequest();
        Integer goods_id = jsonRequest.getInteger("goods_id");
        Integer specification_id = jsonRequest.getInteger("specification_id");
        String value = jsonRequest.getString("value");

        GoodsSpecificationEntity goodsSpecificationEntity = new GoodsSpecificationEntity();

        goodsSpecificationEntity.setGoods_id(goods_id);
        goodsSpecificationEntity.setSpecification_id(specification_id);
        goodsSpecificationEntity.setValue(value);

        goodsSpecificatioService.save(goodsSpecificationEntity);

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("code",200);
        hashMap.put("message","创建成功");
        hashMap.put("data",goodsSpecificationEntity);

        return toResponsSuccess(hashMap);
    }
}
