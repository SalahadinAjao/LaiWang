package com.hlt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: houlintao
 * @Date:2020/7/9 下午6:07
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class ReceiveBookEntity implements Serializable {
    public static final long serialVersionUID = 1L;

    private long id;

    private long userId;
    //账本名，如XXX结婚收礼
    private String name;
    /**
     * 收礼账本类型
     * 1：婚礼 2：生子 3：升学 4：定亲 5：乔迁 6：升迁 7：参军 8：葬礼
     */
    private Integer type;
   /* //出礼人姓名
    private String fromName;
    //出礼金额
    private BigDecimal amount;
    //其他礼物
    private String gift;*/
    //账本序列号
    private long book_sn;

    private Integer orderId;
    /**
     * 这里为什么使用Date而不是String？
     * 因为后期用户需要通过日期查询账本数据，例如通过创建日期 2020/05/20 查询那个账本
     */
    private Date createDate;

    private Date updateDate;
    //账本封面url
    private String cover_img_url;
    /**
     * 账本是否锁定，一旦锁定用户就无法出礼,由创建人随时设定
     * 0：未锁定 1：锁定
     */
    private Integer is_locked;

    public long getId() {
        return id;
    }

    public void setBook_sn(long book_sn) {
        this.book_sn = book_sn;
    }

    public long getBook_sn() {
        return book_sn;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String getCover_img_url() {
        return cover_img_url;
    }

    public Integer getIs_locked() {
        return is_locked;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setCover_img_url(String cover_img_url) {
        this.cover_img_url = cover_img_url;
    }

    public void setIs_locked(Integer is_locked) {
        this.is_locked = is_locked;
    }
}
