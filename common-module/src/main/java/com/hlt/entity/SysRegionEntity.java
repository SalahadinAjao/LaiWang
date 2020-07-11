package com.hlt.entity;

/**
 * @Author: houlintao
 * @Date:2020/7/10 下午4:11
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class SysRegionEntity extends Tree<SysRegionEntity> {

    //主键
    private Integer id;
    //父节点
    private Integer parentId;
    //区域名称
    private String name;
    //类型 0国家 1省份 2地市 3区县
    private Integer type;
    //区域代理Id
    private Integer agencyId;

    //父级名称
    private String parentName;

    public Integer getId() {
        return id;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public Integer getAgencyId() {
        return agencyId;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

}
