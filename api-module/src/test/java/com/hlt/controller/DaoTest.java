package com.hlt.controller;

import com.hlt.dao.*;
import com.hlt.entity.*;
import com.hlt.utils.Query;
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
   private GoodsInOrderDao goodsInOrderDao;

   @Test
   public void queryGoodsInOrder(){

       HashMap param = new HashMap();
       param.put("order_id",20);
       List<GoodsInOrderEntity> goodsInOrderEntities = goodsInOrderDao.queryList(param);
       for (GoodsInOrderEntity entity:goodsInOrderEntities){
           System.out.println(entity.getGoods_name());
       }
   }

}
