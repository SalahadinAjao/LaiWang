package com.hlt.service.Impl;

import com.hlt.dao.TokenDao;
import com.hlt.entity.TokenEntity;
import com.hlt.service.TokenService;
import com.hlt.utils.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/7 下午5:18
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

    private final static Long EXPIRE = 3600 * 24 * 30L;

    @Autowired
    private TokenDao tokenDao;
    @Override
    public void save(TokenEntity entity) {
         tokenDao.save(entity);
    }

    @Override
    public Map<String, Object> createToken(long userId) {
        //使用随机数生成一个token
        String token = CharUtils.getRandomNumStr(232);
        Date nowDate = new Date();
        //设置token过期时间
        Date expireDate = new Date(nowDate.getTime() + EXPIRE * 1000);
        long timeLong = expireDate.getTime();

        TokenEntity tokenEntity = queryByUserId(userId);
        // System.out.printf("tokenEntity="+tokenEntity.getUserId());

        if (tokenEntity != null){
            System.out.println("tokenEntity != null");
            tokenEntity.setUpdateTime(nowDate);
            tokenEntity.setToken(token);
            tokenEntity.setExpireTime(expireDate);
            update(tokenEntity);
        } else {
            System.out.println("tokenEntity == null");
            tokenEntity = new TokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(nowDate);
            tokenEntity.setExpireTime(expireDate);

            save(tokenEntity);
        }

        Date expire = new Date();
        expire.setTime(timeLong);
        SimpleDateFormat format = new SimpleDateFormat("yyyyy-MMMM-dddd hhhh:mmmm:ssss a E");
        String format1 = format.format(expire);

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("token",tokenEntity.getToken());
        hashMap.put("userId",tokenEntity.getUserId());
        // hashMap.put("expireTime",format1);
        return hashMap;
    }

    @Override
    public TokenEntity queryByUserId(long userId) {
        return tokenDao.queryByUserId(userId);
    }

    @Override
    public void update(TokenEntity entity) {
         tokenDao.update(entity);
    }

    @Override
    public TokenEntity queryByToken(String token) {
        return tokenDao.queryByToken(token);
    }
}
