package com.hlt.service;

import com.hlt.entity.AddressEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/8 上午10:34
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface AddressService {

    AddressEntity queryObject(Integer id);

    List<AddressEntity> queryList(Map map);

    void save(AddressEntity addressEntity);

    void update(AddressEntity addressEntity);

    void deleteById(Integer id);

}
