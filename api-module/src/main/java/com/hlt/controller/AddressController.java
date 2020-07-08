package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.CurrentLoginUser;
import com.hlt.entity.AddressEntity;
import com.hlt.entity.UserEntity;
import com.hlt.service.AddressService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/8 上午10:39
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public Object createAddress(@CurrentLoginUser UserEntity loginUser){
        JSONObject jsonRequest = getJsonRequest();
        AddressEntity entity = new AddressEntity();

        if (jsonRequest != null){
            entity.setId(jsonRequest.getInteger("id"));
            entity.setUserId(loginUser.getUserId());
            entity.setUserName(jsonRequest.getString("userName"));
            entity.setPostalCode(jsonRequest.getString("postalCode"));
            entity.setProvinceName(jsonRequest.getString("provinceName"));
            entity.setCityName(jsonRequest.getString("cityName"));
            entity.setCountyName(jsonRequest.getString("countyName"));
            entity.setDetailInfo(jsonRequest.getString("detailInfo"));
            entity.setNationalCode(jsonRequest.getString("nationalCode"));
            entity.setTelNumber(jsonRequest.getString("telNumber"));
            entity.setIs_default(jsonRequest.getInteger("is_default"));
        }
        if (null == entity.getId() || entity.getId() == 0) {
            /**
             * 这里如果entity的id为空，则留给数据库处理，数据库会自增
             */
            entity.setId(null);
            addressService.save(entity);
        } else {
            addressService.update(entity);
        }
        return toResponsSuccess(entity);
    }

    @ResponseBody
    @PostMapping("/list")
    public Object queryList(@CurrentLoginUser UserEntity loginUser){
        Long userId = loginUser.getUserId();

        HashMap query = new HashMap();
        query.put("user_id",userId);
        query.put("sidx",null);
        return addressService.queryList(query);
    }

    @ApiOperation(value = "获取用户详细收货地址",response = Map.class)
    @ApiImplicitParams(@ApiImplicitParam(name = "id",value = "收货地址的id",required = true,dataType = "Integer"))
    @PostMapping("/detail")
    public Object getDetailAddress(@CurrentLoginUser UserEntity loginUser){
        JSONObject jsonRequest = getJsonRequest();
        Integer id = jsonRequest.getInteger("id");
        //通过前端传过来的收货地址id去数据库查找到对应的记录
        AddressEntity addressEntity = addressService.queryObject(id);
        //这里不需要判断 addressEntity 对象是否为空，它一定不为空

        if (!addressEntity.getUserId().equals(loginUser.getUserId())){
            return toResponsObject(407,"越权行为，无权查看","");
        }
        return toResponsSuccess(addressEntity);
    }

    @PostMapping("/delete")
    public Object deleteById(@CurrentLoginUser UserEntity loginUser){
        JSONObject jsonRequest = getJsonRequest();
        Integer id = jsonRequest.getInteger("id");

        AddressEntity addressEntity = addressService.queryObject(id);
        System.out.println("loginUser.name = " + loginUser.getNickname());
        System.out.println("address = " + addressEntity.getCityName());

        if (!loginUser.getUserId().equals(addressEntity.getUserId())){
            return toResponsMsgSuccess("您无权删除");
        }
        addressService.deleteById(id);
        return toResponsSuccess("删除成功");
    }
}
