package com.hlt.controller;

import com.hlt.dao.AddressDao;
import com.hlt.dao.CategoryDao;
import com.hlt.dao.TokenDao;
import com.hlt.dao.UserDao;
import com.hlt.entity.AddressEntity;
import com.hlt.entity.CategoryEntity;
import com.hlt.entity.TokenEntity;
import com.hlt.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: houlintao
 * @Date:2020/7/8 上午6:19
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})
public class DaoTest {
   @Autowired
    private CategoryDao categoryDao;

   @Test
   public void queryObj(){
       Integer id = 1010001;
       CategoryEntity categoryEntity = categoryDao.queryObject(id);
       System.out.println(categoryEntity.getFront_name());
   }
}
