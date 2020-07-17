package com.hlt.controller;

import com.hlt.dao.*;
import com.hlt.entity.*;
import com.hlt.utils.Query;
import com.qiniu.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
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
    private CartDao cartDao;

    @Test
    public void query(){
        int user_id = 15;
        int goods_id = 1152008;
        int product_id = 232;
        HashMap map = new HashMap();
        map.put("user_id",user_id);
        map.put("goods_id",goods_id);
        map.put("product_id",product_id);

        List list = cartDao.queryList(map);
        CartEntity cartEntity = (CartEntity) list.get(0);
        System.out.println(cartEntity.getGoods_name());
    }

    @Test
    public void splitT(){
        String old = "2_5,5_1,3_7,12_9";
        String[] strings = old.split("_");
        for (int i=0;i<strings.length;i++){
            String s = strings[i];
            System.out.println(s);
        }
    }

    @Test
    public void joinT(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("你是好人");
        arrayList.add("好人卡");
        arrayList.add("给你");
        arrayList.add("其实，我还");
        arrayList.add("有一张");
        arrayList.add("绿卡");

        Object[] array = arrayList.toArray();
        String join = StringUtils.join(array, ";");
        System.out.println(join);

    }
}
