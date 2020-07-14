package com.hlt.controller;

import com.hlt.service.GoodsInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: houlintao
 * @Date:2020/7/13 上午9:36
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/goodsinorder")
public class GoodsInOrderController extends BaseController {
    @Autowired
    private GoodsInOrderService goodsInOrderService;

    /*@PostMapping("/save")
    public Object save(){

    }*/
}
