package com.hlt.entity;

import java.io.Serializable;

/**
 * @Author: houlintao
 * @Date:2020/7/17 上午10:38
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class SpecificationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //规范名称
    private String name;
    //排序
    private Integer sort_order;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }
}
