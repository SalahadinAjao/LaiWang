package com.hlt.service.Impl;

import com.hlt.dao.SmsLogDao;
import com.hlt.entity.SmsLogEntity;
import com.hlt.service.SmsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/21 上午9:57
 * @email 437547058@qq.com
 * @Version 1.0
 */
@Service
public class SmsLogServiceImpl implements SmsLogService {
    @Autowired
    private SmsLogDao smsLogDao;
    @Override
    public SmsLogEntity querySmsCodeByUserId(Map<String,Object> param) {
      return smsLogDao.querySmsCodeByUserId(param);
    }

    @Override
    public int save(SmsLogEntity smsLogEntity) {
        return smsLogDao.save(smsLogEntity);
    }
}
