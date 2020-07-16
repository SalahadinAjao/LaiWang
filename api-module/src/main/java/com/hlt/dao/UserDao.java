package com.hlt.dao;

import com.hlt.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: houlintao
 * @Date:2020/7/7 上午11:06
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface UserDao extends BaseDao<UserEntity> {
    @Override
    int save(UserEntity userEntity);

    @Override
    int queryTotal();

    UserEntity queryByMobile(String mobile);

    UserEntity queryObject(@Param("userId") Long id);

    UserEntity queryByUserName(String userName);

    @Override
    int update(UserEntity entity);

    void updatePassword(UserEntity entity);
}
