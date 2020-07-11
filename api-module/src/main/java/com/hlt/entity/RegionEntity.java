package com.hlt.entity;

/**
 * @Author: houlintao
 * @Date:2020/7/10 下午1:42
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class RegionEntity {
    //主键
    private Integer id;
    //父节点
    private Integer parent_id;
    //区域名称
    private String name;
    //类型 0国家 1省份 2地市 3区县
    private Integer type;
    //区域代理Id
    private Integer agency_id;

    public RegionEntity() {
    }

    public RegionEntity(SysRegionEntity regionEntity) {
        id = regionEntity.getId();
        parent_id = regionEntity.getParentId();
        name = regionEntity.getName();
        type = regionEntity.getType();
        agency_id = regionEntity.getAgencyId();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(Integer agency_id) {
        this.agency_id = agency_id;
    }
}
