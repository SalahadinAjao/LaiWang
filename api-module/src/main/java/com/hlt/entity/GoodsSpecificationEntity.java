package com.hlt.entity;

import java.io.Serializable;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午11:07
 * @email 437547058@qq.com
 * @Version 1.0
 * 商品规格表，就是商品规格数据的封装
 */
public class GoodsSpecificationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    //商品id
    private Integer goods_id;
    //规格id
    private Integer specification_id;
    //规格值
    private String value;
    //规格图片
    private String pic_url;

    public Integer getId() {
        return id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public Integer getSpecification_id() {
        return specification_id;
    }

    public String getValue() {
        return value;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public void setSpecification_id(Integer specification_id) {
        this.specification_id = specification_id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
