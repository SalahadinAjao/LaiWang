package com.hlt.service;

import com.hlt.entity.UserEntity;

/**
 * @Author: houlintao
 * @Date:2020/7/7 上午11:17
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface UserService {
    int save(UserEntity userVo);

    int save(String mobile,String password);

    int queryTotal();

    UserEntity queryByMobile(String mobile);

    UserEntity queryObject(Long userId);

    UserEntity queryByUserName(String userName);
}
