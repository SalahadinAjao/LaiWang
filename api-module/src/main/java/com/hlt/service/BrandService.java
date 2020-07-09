package com.hlt.service;

import com.hlt.entity.BrandEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午7:33
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface BrandService {

    int save(BrandEntity entity);

    List<BrandEntity> queryList(Map<String,Object> map);

    int queryTotal(Map<String,Object> map);
}
