package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.CurrentLoginUser;
import com.hlt.entity.GoodsInOrderEntity;
import com.hlt.entity.OrderEntity;
import com.hlt.entity.UserEntity;
import com.hlt.service.GoodsInOrderService;
import com.hlt.service.OrderService;
import com.hlt.service.ShippingBirdService;
import com.hlt.utils.ShippingIdGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private GoodsInOrderService goodsInOrderService;
    @Autowired
    private ShippingBirdService birdService;


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
        entity.setAdd_time(time);
        Integer orderstatus = jsonRequest.getInteger("orderstatus");
        entity.setOrder_status(orderstatus);


        Integer shippingstatus = jsonRequest.getInteger("shippingstatus");
        entity.setShipping_status(shippingstatus);

        String shippingcode = jsonRequest.getString("shippingcode");
        entity.setShipping_code(shippingcode);

        String shipping_no = jsonRequest.getString("shipping_no");
        entity.setShipping_no(shipping_no);

        Integer shipping_id = jsonRequest.getInteger("shipping_id");
        entity.setShipping_id(shipping_id);

        String shipping_name = jsonRequest.getString("shipping_name");
        entity.setShipping_name(shipping_name);
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

        String order_type = jsonRequest.getString("order_type");
        entity.setOrder_type(order_type);

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

    /**
     * 根据订单id获取对应订单详情，包括订单信息和快递信息
     */
    @PostMapping("/detail")
    public Object detailInfo(@CurrentLoginUser UserEntity loginUser,Integer orderId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (orderId == null){
            return toResponsFail("请输入订单id");
        }
        //根据id查询对应的订单实体
        OrderEntity entity = orderService.queryObject(orderId);

        if (entity == null){
            return toResponsFail("订单不存在");
        }
        //判断订单的userId与当前操作的userId是否相同
        if (!loginUser.equals(entity.getUser_id())){
            return toResponsFail("无权查看");
        }
        //如果满足上述条件则处理订单中的商品
        HashMap param = new HashMap();
        param.put("order_id",orderId);

        Map resultMap = new HashMap();
        //一个order对象中可能包含多个商品
        List<GoodsInOrderEntity> list = goodsInOrderService.queryList(param);
        //如果订单创建成功，等待付款
        if (entity.getOrder_status()==0){
            //此时用户可以对订单对象进行的操作有继续付款，或者取消订单
            Map handleOption = entity.getHandleOption();

            resultMap.put("orderEntity",entity);
            resultMap.put("goodsEntities",list);
            resultMap.put("handlerOPtions",handleOption);

            /**
             * 获取订单的快递信息,由于需要调用快递鸟的API获取即时物流追踪信息，因此需要保证订单对应的物流公司代码
             * 和物流单号不为空，这是快递鸟api的硬性要求;
             */
            if (!StringUtils.isEmpty(entity.getShipping_code()) && !StringUtils.isEmpty(entity.getShipping_no())){
                List traces = birdService.getOrderTracesByJson(entity.getShipping_code(), entity.getShipping_no());

                if (traces == null){
                    return toResponsObject(400,"没有此订单的快递信息，请核对","");
                }
                resultMap.put("shippingTraces",traces);
            }
        }
        return toResponsSuccess(resultMap);
    }
}
