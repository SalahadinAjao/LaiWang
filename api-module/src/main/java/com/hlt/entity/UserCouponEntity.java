package com.hlt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: houlintao
 * @Date:2020/7/17 下午4:10
 * @email 437547058@qq.com
 * @Version 1.0
 * 用户领取的优惠券实体类，它描述的是平台上消费者领取的优惠券信息，与商家定义的优惠券 CouponEntity 类对应，
 * 商户发布优惠券(CouponEntity)，消费者领取后就是 UserCouponEntity
 */
public class UserCouponEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //优惠券Id
    private Integer coupon_id;
    //优惠券号码
    private String coupon_number;
    //用户Id
    private Long user_id;
    //使用时间
    private Date used_time;
    //领取时间
    private Date add_time;
    //订单Id
    private Integer order_id;
    //来源key
    private String source_key;
    //分享人
    private Long referrer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Integer coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_number() {
        return coupon_number;
    }

    public void setCoupon_number(String coupon_number) {
        this.coupon_number = coupon_number;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getUsed_time() {
        return used_time;
    }

    public void setUsed_time(Date used_time) {
        this.used_time = used_time;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }


    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public String getSource_key() {
        return source_key;
    }

    public void setSource_key(String source_key) {
        this.source_key = source_key;
    }

    public Long getReferrer() {
        return referrer;
    }

    public void setReferrer(Long referrer) {
        this.referrer = referrer;
    }
}
