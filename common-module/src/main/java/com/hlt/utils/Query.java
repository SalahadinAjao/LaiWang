package com.hlt.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午8:28
 * @email 437547058@qq.com
 * @Version 1.0
 * 数据库复杂查询参数封装类
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    //当前页码
    private int page;
    //每页限定查询条数，默认为10
    private int limit=10;

    public Query(Map<String,Object> map){
        this.putAll(map);

        //设置分页参数
        this.page=Integer.parseInt(map.get("page").toString());
        this.limit = Integer.parseInt(map.get("limit").toString());

        //设置每页的查询起点
        this.put("offset",(page-1)*limit);
        //当前页码
        this.put("page",page);
        this.put("limit",limit);

        //设置结果的排序规则
        String sidx = map.get("sidx").toString();
        String order = map.get("order").toString();

        /**
         * 在这一步需要主义sql注入，由于sidx、order是通过拼接SQL实现排序的，会
         * 有SQL注入风险，因此需要有个过滤器。
         */
        this.put("sidx",SqlFilter.SqlInject(sidx));
        this.put("order",SqlFilter.SqlInject(order));
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
