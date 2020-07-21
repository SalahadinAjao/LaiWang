package com.hlt.service;

import com.hlt.entity.SmsLogEntity;

import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/21 上午9:55
 * @email 437547058@qq.com
 * @Version 1.0
 */
public interface SmsLogService {
    SmsLogEntity querySmsCodeByUserId(Map<String,Object> param);

    int save(SmsLogEntity smsLogEntity);
}
