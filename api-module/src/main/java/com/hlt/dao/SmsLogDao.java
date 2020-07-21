package com.hlt.dao;

import com.hlt.entity.SmsLogEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/21 上午9:43
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Repository
public interface SmsLogDao extends BaseDao<SmsLogEntity> {
    //返回的是SmsLogEntity对象，验证码直接从此对象中取，获取数据库中相同 user_id 的最新smsCode
    SmsLogEntity querySmsCodeByUserId(Map<String,Object> param);

    int save(SmsLogEntity smsLogEntity);

    SmsLogEntity queryTest();
}
