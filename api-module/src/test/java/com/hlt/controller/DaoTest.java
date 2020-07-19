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
    private CouponDao couponDao;

    @Test
    public void queryCouponList(){
        HashMap param = new HashMap();
        param.put("user_id",31);
        List<CouponEntity> list = couponDao.queryCouponList(param);
        System.out.println(list.size());
    }

    @Test
    public void queryMaxUserEnableCoupon(){
        HashMap param = new HashMap();
        param.put("send_type",4);
        CouponEntity couponEntity = couponDao.queryMaxUserEnableCoupon(param);
        System.out.println(couponEntity.getName());
    }

    @Test
    public void queryUserCoupons(){
        HashMap param = new HashMap();
        param.put("user_id",31);
        List<CouponEntity> list = couponDao.queryUserCoupons(param);
        System.out.println(list.size());

        for (CouponEntity couponEntity:list){
            System.out.println(couponEntity.getName());
        }
    }

    @Test
    public void getUserCoupon(){
        CouponEntity userCoupon = couponDao.getUserCoupon(70);
        System.out.println(userCoupon.getName());
    }

    @Test
    public void save(){
        CouponEntity coupon = new CouponEntity();
        coupon.setName("中秋团圆惠");
        coupon.setSend_type(4);
        coupon.setType_money(new BigDecimal(5));
        coupon.setMin_amount(new BigDecimal(50));
        coupon.setMax_amount(new BigDecimal(0));
        coupon.setMin_goods_amount(new BigDecimal(99));

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,8,15,03,11,32);
        Date startTime = calendar.getTime();
        coupon.setSend_start_date(startTime);

        calendar.set(2020,8,25,03,59,59);
        Date stopTIme = calendar.getTime();
        coupon.setSend_end_date(stopTIme);

        calendar.set(2020,8,19,00,10,00);
        Date ues_start = calendar.getTime();
        coupon.setUse_start_date(ues_start);

        calendar.set(2020,10,29,10,00,59);
        Date use_end = calendar.getTime();
        coupon.setUse_end_date(use_end);
        //coupon.setMin_transmit_num(1);

        couponDao.save(coupon);

        System.out.println("保存成功");
    }

    @Autowired
    private UserCouponDao userCouponDao;

    @Test
    public void insertUserCoupon(){
        UserCouponEntity userCoupon = new UserCouponEntity();
        userCoupon.setCoupon_id(1);
        userCoupon.setCoupon_number("TJ02200059");
        userCoupon.setOrder_id(6);
        userCoupon.setUser_id(40L);

        Calendar instance = Calendar.getInstance();
        instance.set(2020,05,13,19,12,49);
        Date add_time = instance.getTime();

        userCoupon.setAdd_time(add_time);

        instance.set(2020,05,16,32,17,28);
        Date used_time = instance.getTime();

        userCoupon.setUsed_time(used_time);
        userCoupon.setSource_key("微信分享");
        userCoupon.setReferrer(28L);

        userCouponDao.save(userCoupon);
        System.out.println("插入完成");
    }
    @Test
    public void queryList(){
        HashMap param = new HashMap();
        param.put("user_id",58);

        List<UserCouponEntity> list = userCouponDao.queryList(param);

        for (UserCouponEntity userCoupon:list){
            System.out.println(userCoupon.getCoupon_id());
        }
    }
    @Autowired
    private OrderDao orderDao;

    @Test
    public void saveOrder(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,07,19,9,18,57);
        Date snDate = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        String sn = simpleDateFormat.format(snDate);

        OrderEntity order = new OrderEntity();
        order.setOrder_sn(sn);
        order.setUser_id(60L);
        order.setOrder_status(301);
        order.setShipping_status(2);
        order.setPay_status(2);
        order.setConsignee("卓成永");
        order.setCountry("中国");
        order.setProvince("辽宁省");
        order.setCity("开元市");
        order.setDistrict("松山镇");
        order.setAddress("象牙山村大脚超市");
        order.setMobile("133********");
        order.setPostscript("放大脚超市");
        order.setShipping_id(80);
        order.setShipping_name("圆通速递");
        order.setShipping_code("YTO");
        order.setShipping_no("YTO2020071909252331");
        order.setShipping_fee(new BigDecimal(5));
        order.setActual_price(new BigDecimal(40));
        order.setOrder_total_price(new BigDecimal(45));
        order.setGoods_total_price(new BigDecimal(40));

        orderDao.save(order);
    }


}
