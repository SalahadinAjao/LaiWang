package com.hlt.entity;

import java.io.Serializable;

/**
 * @Author: houlintao
 * @Date:2020/7/10 上午8:22
 * @email 437547058@qq.com
 * @Version 1.0
 * 购买的商品，这个是每次用户添加待购买的商品和完成购物后的所购买的商品
 */
public class GoodsPurchasedEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer goodsId;
    private Integer productId;
    private Integer number;
    private String name;


    public Integer getGoodsId() {
        return goodsId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }
}
