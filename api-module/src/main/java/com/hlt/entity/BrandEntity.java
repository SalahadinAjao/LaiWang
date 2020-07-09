package com.hlt.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午7:20
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //品牌名称
    private String name;
    //图片列表url
    private String list_pic_url;
    //描述
    private String simple_desc;
    //图片
    private String pic_url;
    //排序
    private Integer sort_order;
    //是否显示
    private Integer is_show;
    //地板价
    private BigDecimal floor_price;
    //app显示图片
    private String app_list_pic_url;
    //是否是新品牌
    private Integer is_new;
    //图片
    private String new_pic_url;
    //新的排序
    private Integer new_sort_order;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getList_pic_url() {
        return list_pic_url;
    }

    public String getSimple_desc() {
        return simple_desc;
    }

    public String getPic_url() {
        return pic_url;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public BigDecimal getFloor_price() {
        return floor_price;
    }

    public String getApp_list_pic_url() {
        return app_list_pic_url;
    }

    public Integer getIs_new() {
        return is_new;
    }

    public String getNew_pic_url() {
        return new_pic_url;
    }

    public Integer getNew_sort_order() {
        return new_sort_order;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList_pic_url(String list_pic_url) {
        this.list_pic_url = list_pic_url;
    }

    public void setSimple_desc(String simple_desc) {
        this.simple_desc = simple_desc;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    public void setFloor_price(BigDecimal floor_price) {
        this.floor_price = floor_price;
    }

    public void setApp_list_pic_url(String app_list_pic_url) {
        this.app_list_pic_url = app_list_pic_url;
    }

    public void setIs_new(Integer is_new) {
        this.is_new = is_new;
    }

    public void setNew_pic_url(String new_pic_url) {
        this.new_pic_url = new_pic_url;
    }

    public void setNew_sort_order(Integer new_sort_order) {
        this.new_sort_order = new_sort_order;
    }
}
