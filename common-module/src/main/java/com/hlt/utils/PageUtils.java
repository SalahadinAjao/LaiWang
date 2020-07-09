package com.hlt.utils;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午9:17
 * @email 437547058@qq.com
 * @Version 1.0
 * 分页工具类
 */
public class PageUtils implements Serializable {

    private static final long serialVersionUID = 1L;
    //总记录数
    private int count;
    //每页记录数
    private int numsPerPage;
    //总页数
    private int totalPages;
    //当前页数
    private int currentPage;
    //列表数据
    private List<?> data;
    //扩展，过滤器分类
    private Object filterCategory;
    //扩展，商品列表
    private Object goodsList;

    /**
     * 分页
     *
     * @param list        列表数据
     * @param count       总记录数
     * @param numsPerPage 每页记录数
     * @param currentPage 当前页数
     */
    public PageUtils(List<?> list, int count, int numsPerPage, int currentPage) {
        this.data = list;
        this.count = count;
        this.numsPerPage = numsPerPage;
        this.currentPage = currentPage;
        this.totalPages = (int) Math.ceil((double) count / numsPerPage);
    }

    public PageUtils(PageInfo pageInfo) {
        this.count = (int) pageInfo.getTotal();
        this.numsPerPage = pageInfo.getPageSize();
        this.currentPage = pageInfo.getPageNum();
        this.totalPages = pageInfo.getPages();
    }

    public int getCount() {
        return count;
    }

    public int getNumsPerPage() {
        return numsPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<?> getData() {
        return data;
    }

    public Object getFilterCategory() {
        return filterCategory;
    }

    public Object getGoodsList() {
        return goodsList;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setNumsPerPage(int numsPerPage) {
        this.numsPerPage = numsPerPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public void setFilterCategory(Object filterCategory) {
        this.filterCategory = filterCategory;
    }

    public void setGoodsList(Object goodsList) {
        this.goodsList = goodsList;
    }
}
