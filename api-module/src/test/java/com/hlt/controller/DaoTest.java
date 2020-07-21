package com.hlt.controller;

import com.hlt.dao.*;
import com.hlt.entity.*;
import com.hlt.service.CouponService;
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
import java.text.ParseException;
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
    private SmsLogDao smsLogDao;
    @Autowired
    private CouponDao couponDao;
    @Test
    public void save() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,06,18,11,25,13);
        Date time = calendar.getTime();
        String formatTime = simpleDateFormat.format(time);

        long longTime = simpleDateFormat.parse(formatTime).getTime();


        SmsLogEntity smsLog = new SmsLogEntity();
        smsLog.setUser_id(45L);
        smsLog.setPhone("13355003033");
        smsLog.setSend_status(1);
        smsLog.setSms_code(1949);
        smsLog.setSms_text("发送成功");
        smsLog.setLog_date(longTime);

        int save = smsLogDao.save(smsLog);
        System.out.println(save);
    }

    @Test
    public void queryLastSms(){
        HashMap param = new HashMap();
        param.put("user_id",45L);
        SmsLogEntity smsLogEntity = smsLogDao.querySmsCodeByUserId(param);
        System.out.println(smsLogEntity.getLog_date());
    }

}
