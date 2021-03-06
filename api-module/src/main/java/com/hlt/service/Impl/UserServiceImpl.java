package com.hlt.service.Impl;

import com.hlt.dao.UserDao;
import com.hlt.entity.UserEntity;
import com.hlt.service.UserService;
import com.hlt.utils.RRException;
import com.hlt.validators.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: houlintao
 * @Date:2020/7/7 上午11:18
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 3)
    public int save(UserEntity userVo) {
        return userDao.save(userVo);
    }

    @Override
    public int save(String mobile, String password) {
        return 0;
    }

    @Override
    public int queryTotal() {
        return userDao.queryTotal();
    }

    @Override
    public UserEntity queryByMobile(String mobile) {
        return userDao.queryByMobile(mobile);
    }

    @Override
    public UserEntity queryObject(Long userId) {
        return userDao.queryObject(userId);
    }

    @Override
    public UserEntity queryByUserName(String userName) {
        return userDao.queryByUserName(userName);
    }

    @Override
    public long login(String mobile, String password) {
        UserEntity userEntity = queryByMobile(mobile);

        Assert.isNull(userEntity,"手机号或密码错误");

        if (!userEntity.getPassword().equals(DigestUtils.sha256Hex(password))){
            throw new RRException("手机号或密码错误");
        }
        return userEntity.getUserId();
    }

    @Override
    public void update(UserEntity entity) {
        userDao.update(entity);
    }

    @Override
    public void updatePassword(UserEntity entity) {
         userDao.updatePassword(entity);
    }
}
