package com.hlt.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: houlintao
 * @Date:2020/7/9 下午6:31
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class ReceiveRecordEntity implements Serializable {
    public static final long serialVersionUID = 1L;

    private long id;
    //收礼账本id
    private long receive_book_id;
    //出礼人姓名
    private String fromWho;
    //出礼金额
    private BigDecimal amount;
    //其他礼物
    private String gift;

}
