package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.CurrentLoginUser;
import com.hlt.entity.OrderEntity;
import com.hlt.entity.UserEntity;
import com.hlt.service.OrderService;
import com.hlt.utils.ShippingIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Author: houlintao
 * @Date:2020/7/14 上午7:02
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;


    @PostMapping("save")
    public Object save(@CurrentLoginUser UserEntity loginUser){

        JSONObject jsonRequest = getJsonRequest();
        Integer year = jsonRequest.getInteger("year");
        Integer month = jsonRequest.getInteger("month");
        Integer date = jsonRequest.getInteger("date");
        Integer hour = jsonRequest.getInteger("hour");
        Integer minute = jsonRequest.getInteger("minute");
        Integer second = jsonRequest.getInteger("second");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,date,hour,minute,second);
        Date time = calendar.getTime();

        String order_sn = simpleDateFormat.format(time);
        Long user_id = loginUser.getUserId();

        OrderEntity entity = new OrderEntity();
        entity.setOrder_sn(order_sn);
        entity.setUser_id(user_id);

        Integer orderstatus = jsonRequest.getInteger("orderstatus");
        entity.setOrder_status(orderstatus);

        Integer shippingstatus = jsonRequest.getInteger("shippingstatus");
        entity.setShipping_status(shippingstatus);

        Integer paystatus = jsonRequest.getInteger("paystatus");
        entity.setPay_status(paystatus);

        String consignee = jsonRequest.getString("consignee");
        entity.setConsignee(consignee);

        String country = jsonRequest.getString("country");
        entity.setCountry(country);

        String province = jsonRequest.getString("province");
        entity.setProvince(province);

        String city = jsonRequest.getString("city");
        entity.setCity(city);

        String district = jsonRequest.getString("district");
        entity.setDistrict(district);

        String address = jsonRequest.getString("address");
        entity.setAddress(address);

        String mobile = jsonRequest.getString("mobile");
        entity.setMobile(mobile);

        String postscript = jsonRequest.getString("postscript");
        entity.setPostscript(postscript);

        Integer shippingid = jsonRequest.getInteger("shippingid");
        entity.setShipping_id(shippingid);

        String shippingname = jsonRequest.getString("shippingname");
        entity.setShipping_name(shippingname);

        String pay_id = ShippingIdGenerator.generateShippingId();
        entity.setPay_id(pay_id);

        String payname = jsonRequest.getString("payname");
        entity.setPay_name(payname);

        BigDecimal fee = jsonRequest.getBigDecimal("fee");
        entity.setShipping_fee(fee);

        BigDecimal actual_price = jsonRequest.getBigDecimal("actual_price");
        entity.setActual_price(actual_price);

        entity.setIntegral(0);
        entity.setIntegral_money(new BigDecimal(0));

        BigDecimal order_total_price = jsonRequest.getBigDecimal("order_total_price");
        entity.setOrder_total_price(order_total_price);

        BigDecimal goods_total_price = jsonRequest.getBigDecimal("goods_total_price");
        entity.setGoods_total_price(goods_total_price);

       orderService.save(entity);

       return toResponsObject(200,"订单保存成功",entity);
    }
    /**
     * 确认收货
     */
    @PostMapping("/confirm")
    public Object confirmOrder(@CurrentLoginUser UserEntity loginUser,Integer orderId){

        try {
            OrderEntity orderEntity = orderService.queryObject(orderId);

            if (!loginUser.getUserId().equals(orderEntity.getUser_id())){
                return toResponsFail("越权行为，无法操作其他人的订单");
            }

            orderEntity.setOrder_status(301);
            orderEntity.setShipping_status(2);
            orderEntity.setConfirm_time(new Date());

            orderService.update(orderEntity);
            return toResponsSuccess("确认收货成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return toResponsFail("提交失败");
    }
}
