package com.hlt.dao;

import com.hlt.entity.AddressEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/8 上午10:25
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface AddressDao extends BaseDao<AddressEntity>{

    @Override
    List<AddressEntity> queryList(Map map);

    AddressEntity queryObject(Integer id);

    @Override
    int save(AddressEntity addressEntity);

    int deleteById(Object id);
}
