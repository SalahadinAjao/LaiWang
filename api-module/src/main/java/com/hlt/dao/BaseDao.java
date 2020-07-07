package com.hlt.dao;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/7 上午11:06
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface BaseDao<T> {
    int save(T t);

    void save(Map<String, Object> map);

    void saveBatch(List<T> list);

    int update(T t);

    int update(Map<String, Object> map);

    int delete(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    T queryObject(Object id);

    List<T> queryList(Map<String, Object> map);

    List<T> queryList(Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();
}
