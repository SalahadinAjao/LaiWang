package com.hlt.service.Impl;

import com.hlt.dao.AddressDao;
import com.hlt.entity.AddressEntity;
import com.hlt.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/8 上午10:37
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public AddressEntity queryObject(Integer id) {
        return addressDao.queryObject(id);
    }

    @Override
    public List<AddressEntity> queryList(Map map) {
        return addressDao.queryList(map);
    }

    @Override
    public void save(AddressEntity addressEntity) {
        addressDao.save(addressEntity);
    }

    @Override
    public void update(AddressEntity addressEntity) {
        addressDao.update(addressEntity);
    }

    @Override
    public void deleteById(Integer id) {
        addressDao.deleteById(id);
    }
}
