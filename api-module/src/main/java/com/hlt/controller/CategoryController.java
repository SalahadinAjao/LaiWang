package com.hlt.controller;

import com.hlt.annotations.SkipAuth;
import com.hlt.entity.CategoryEntity;
import com.hlt.service.CategoryService;
import com.hlt.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/10 上午11:20
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;

    @SkipAuth
    @PostMapping("/index")
    public Object index(@RequestParam("id") Integer id,@RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size){
        HashMap<String, Object> returnObj = new HashMap<>();

        HashMap params = new HashMap();
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "sort_order");
        params.put("order", "asc");
        params.put("parent_id", 0);

        Query query = new Query(params);
        //根据条件查询分类列表
        List<CategoryEntity> categoryEntities = categoryService.queryList(query);
        //当前分类对象，就是当前的category的index页面展开的那个分类对象
        CategoryEntity currentCategory = null;
        //如果从前端明确传过去一个点开的分类的id
        if (id != null){
            //如果传过来的id不是null，就通过它查找分类对象
            currentCategory = categoryService.queryObject(id);
        }
        if (currentCategory == null && categoryEntities != null && categoryEntities.size()!=0){
            //如果没有特意指定，直接展开第一个分类，就是get(0)返回的那个分类
            currentCategory=categoryEntities.get(0);
        }else {
            currentCategory=new CategoryEntity();
        }
        //获取该分类对象下的子分类
        if (currentCategory != null && currentCategory.getId() != null){
            params.put("parent_id",currentCategory.getId());
            currentCategory.setSubCategoryList(categoryService.queryList(params));
        }
        returnObj.put("categoryList",categoryEntities);
        returnObj.put("currentCategory",currentCategory);

        return toResponsSuccess(returnObj);
    }

    @SkipAuth
    @PostMapping("/current")
    public Object currentCategory(@RequestParam("id") Integer id){
        HashMap returnObj = new HashMap();

        Map params = new HashMap();

        CategoryEntity currentCategory = null;

        if (id != null){
            currentCategory = categoryService.queryObject(id);
        }
        if (currentCategory != null && currentCategory.getId() != null){
            params.put("parent_id",currentCategory.getId());

            currentCategory.setSubCategoryList(categoryService.queryList(params));
        }

        returnObj.put("currentCategory", currentCategory);

        return toResponsSuccess(returnObj);
    }
}
