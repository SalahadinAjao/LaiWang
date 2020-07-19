package com.hlt.controller;

import com.hlt.dao.*;
import com.hlt.entity.*;
import com.hlt.utils.Constants;
import com.hlt.utils.DateUtils;
import com.hlt.utils.Query;
import com.qiniu.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private UserCouponDao userCouponDao;

    @Test
    public void queryByCouponNumber(){
        UserCouponEntity userCouponEntity = userCouponDao.queryByCouponNumber("HUJ57321");
        System.out.println(userCouponEntity.getAdd_time());
    }
}
