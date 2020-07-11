package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.SkipAuth;
import com.hlt.cache.RegionCacheUtil;
import com.hlt.entity.RegionEntity;
import com.hlt.entity.SysRegionEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/10 下午1:45
 * @email 437547058@qq.com
 * @Version 1.0
 */

@RestController
@RequestMapping("/region")
public class RegionController extends BaseController{

    @SkipAuth
    @PostMapping("/list")
    public Object list(@RequestParam("parentId") Integer parentId) {

        List<SysRegionEntity> regionEntityList = RegionCacheUtil.getChildrenByParentId(parentId);

        System.out.println("regionEntityList的长度 = " + regionEntityList.size());

        List<RegionEntity> regionVoList = new ArrayList<RegionEntity>();

        if (null != regionEntityList && regionEntityList.size() > 0) {

            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionEntity(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    @SkipAuth
    @PostMapping("/provinceList")
    public Object provinceList() {
        List<SysRegionEntity> regionEntityList = RegionCacheUtil.getAllProvice();
        List<RegionEntity> regionVoList = new ArrayList<RegionEntity>();
        if (null != regionEntityList && regionEntityList.size() > 0) {
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionEntity(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    //查找指定省份下面的地级市
    @SkipAuth
    @PostMapping("/cityList")
    public Object cityList() {
        JSONObject jsonRequest = getJsonRequest();
        String proviceName = jsonRequest.getString("proviceName");

        List<SysRegionEntity> regionEntityList = RegionCacheUtil.getChildrenCity(proviceName);

        List<RegionEntity> regionVoList = new ArrayList<RegionEntity>();

        if (null != regionEntityList && regionEntityList.size() > 0) {
            System.out.println("regionEntityList长度 = " + regionEntityList.size());
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionEntity(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    //根据省市名查找下面的区县
    @SkipAuth
    @PostMapping("/distinctList")
    public Object distinctList(String proviceName, String cityName) {
        List<SysRegionEntity> regionEntityList = RegionCacheUtil.getChildrenDistrict(proviceName, cityName);
        List<RegionEntity> regionVoList = new ArrayList<RegionEntity>();
        if (null != regionEntityList && regionEntityList.size() > 0) {
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionEntity(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    @SkipAuth
    @PostMapping("/info")
    public Object info(Integer regionId) {
        SysRegionEntity regionEntity = RegionCacheUtil.getAreaByAreaId(regionId);
        return toResponsSuccess(new RegionEntity(regionEntity));
    }


}
