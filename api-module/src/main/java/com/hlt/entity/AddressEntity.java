package com.hlt.entity;

import java.io.Serializable;

/**
 * @Author: houlintao
 * @Date:2020/7/8 上午10:23
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //会员ID
    private Long userId;
    //收货人姓名
    private String userName;
    //手机
    private String telNumber;
    //邮编
    private String postalCode;
    //收货地址国家码
    private String nationalCode;
    //省
    private String provinceName;
    //市
    private String cityName;
    //区
    private String countyName;
    //详细收货地址信息
    private String detailInfo;

    //默认
    private Integer is_default = 0;


    public Integer getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public Integer getIs_default() {
        return is_default;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }
}
