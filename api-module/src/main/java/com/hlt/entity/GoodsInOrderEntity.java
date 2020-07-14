package com.hlt.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: houlintao
 * @Date:2020/7/13 上午9:06
 * @email 437547058@qq.com
 * @Version 1.0
 * 订单中的商品，即每笔订单中包含的商品信息,它描述的主体是商品而不是订单；
 * 一个订单可以包含多个商品，但一个订单中的商品只可以属于一个订单，只有一个orderId；
 */
public class GoodsInOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //订单Id
    private Integer order_id;
    //商品id
    private Integer goods_id;
    //商品名称
    private String goods_name;
    //商品序列号
    private String goods_sn;
    //产品Id
    private Integer product_id;
    //商品数量
    private Integer quantity;
    //市场价
    private BigDecimal market_price;
    //零售价格
    private BigDecimal retail_price;
    //商品规格详情
    private String goods_specifition_name_value;
    //是否是虚拟商品
    private Integer is_real;
    //商品规格Ids
    private String goods_specifition_ids;
    //图片链接
    private String list_pic_url;

    public Integer getId() {
        return id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getMarket_price() {
        return market_price;
    }

    public BigDecimal getRetail_price() {
        return retail_price;
    }

    public String getGoods_specifition_name_value() {
        return goods_specifition_name_value;
    }

    public Integer getIs_real() {
        return is_real;
    }

    public String getGoods_specifition_ids() {
        return goods_specifition_ids;
    }

    public String getList_pic_url() {
        return list_pic_url;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setMarket_price(BigDecimal market_price) {
        this.market_price = market_price;
    }

    public void setRetail_price(BigDecimal retail_price) {
        this.retail_price = retail_price;
    }

    public void setGoods_specifition_name_value(String goods_specifition_name_value) {
        this.goods_specifition_name_value = goods_specifition_name_value;
    }

    public void setIs_real(Integer is_real) {
        this.is_real = is_real;
    }

    public void setGoods_specifition_ids(String goods_specifition_ids) {
        this.goods_specifition_ids = goods_specifition_ids;
    }

    public void setList_pic_url(String list_pic_url) {
        this.list_pic_url = list_pic_url;
    }
}
