package com.hlt.service.Impl;

import com.hlt.dao.BrandDao;
import com.hlt.entity.BrandEntity;
import com.hlt.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/9 上午7:34
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;
    @Override
    public int save(BrandEntity entity) {
        return brandDao.save(entity);
    }

    @Override
    public List<BrandEntity> queryList(Map<String, Object> map) {
       return brandDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return brandDao.queryTotal(map);
    }

    @Override
    public BrandEntity queryObject(Integer id) {
        return brandDao.queryObject(id);
    }
}
