package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.CurrentLoginUser;
import com.hlt.entity.BrandEntity;
import com.hlt.entity.UserEntity;
import com.hlt.service.BrandService;
import com.hlt.utils.PageUtils;
import com.hlt.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午7:35
 * @email 437547058@qq.com
 * @Version 1.0
 */

@RestController
@RequestMapping("/brand")
public class BrandController extends BaseController {
    @Autowired
    private BrandService brandService;

    @PostMapping("/create")
    public Object createBrand(@CurrentLoginUser UserEntity loginUser){
        JSONObject jsonRequest = getJsonRequest();
        String name = jsonRequest.getString("name");
        String list_pic_url = jsonRequest.getString("list_pic_url");
        String simple_desc = jsonRequest.getString("simple_desc");
        String pic_url = jsonRequest.getString("pic_url");
        Integer sort_order = jsonRequest.getInteger("sort_order");
        Integer is_show = jsonRequest.getInteger("is_show");
        BigDecimal floor_price = jsonRequest.getBigDecimal("floor_price");
        String app_list_pic_url = jsonRequest.getString("app_list_pic_url");
        Integer is_new = jsonRequest.getInteger("is_new");
        String new_pic_url = jsonRequest.getString("new_pic_url");
        Integer new_sort_order = jsonRequest.getInteger("new_sort_order");

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName(name);
        brandEntity.setList_pic_url(list_pic_url);
        brandEntity.setSimple_desc(simple_desc);
        brandEntity.setPic_url(pic_url);
        brandEntity.setSort_order(sort_order);
        brandEntity.setIs_show(is_show);
        brandEntity.setFloor_price(floor_price);
        brandEntity.setApp_list_pic_url(app_list_pic_url);
        brandEntity.setIs_new(is_new);
        brandEntity.setNew_pic_url(new_pic_url);
        brandEntity.setNew_sort_order(new_sort_order);

        brandService.save(brandEntity);

        return toResponsObject(200,"新建品牌成功",brandEntity);
    }

    /**
     * 分页查询品牌数据
     * 使用 @RequestParam：将请求参数绑定到你控制器的方法参数上，这个只能把形如：
     * http://localhost:8080/brand/querylist?page=2&size=5 这样的参数与url
     * 一起发送的参数解析出来。
     */
    @PostMapping("/querylist")
    public Object queryBrand(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size){

        //创建一个Map作为请求数据的容器
        HashMap<String, Object> param = new HashMap<>();
        param.put("fields", "id, name, floor_price, app_list_pic_url");
        param.put("page",page);
        param.put("limit",size);
        param.put("sidx","id");
        param.put("order","asc");

        Query query = new Query(param);

        List<BrandEntity> brandEntities = brandService.queryList(query);
        int total = brandService.queryTotal(query);

        PageUtils pageUtils = new PageUtils(brandEntities, total, query.getLimit(), query.getPage());

        return toResponsSuccess(pageUtils);
    }

    @PostMapping("/detail")
    public Object detail(@RequestParam Integer id){
        BrandEntity brandEntity = brandService.queryObject(id);
        return toResponsSuccess(brandEntity);
    }
}
