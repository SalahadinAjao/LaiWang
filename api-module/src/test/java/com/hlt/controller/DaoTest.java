package com.hlt.controller;

import com.hlt.dao.*;
import com.hlt.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

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
    private SysRegionDao sysRegionDao;
   @Test
   public void queryList(){
       HashMap hashMap = new HashMap();
       List list = sysRegionDao.queryList(hashMap);
       System.out.println(list.get(1));
   }

   @Test
   public void queryObject(){
       SysRegionEntity sysRegionEntity = sysRegionDao.queryObject(1);
       System.out.println(sysRegionEntity.getName());
   }
}
