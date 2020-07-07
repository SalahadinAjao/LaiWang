package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.entity.UserEntity;
import com.hlt.service.UserService;
import com.hlt.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @Author: houlintao
 * @Date:2020/7/7 上午11:22
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Object register() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JSONObject jsonRequest = getJsonRequest();
        String userName = jsonRequest.getString("userName");
        String passWord = jsonRequest.getString("passWord");
        //判断用户名是否重复
        UserEntity userEntity = userService.queryByUserName(userName);

        if (userEntity != null){
            return toResponsObject(401,"用户名已存在","");
        }else {

            MD5 md5 = new MD5();

            String newPass = md5.EncodeByMd5(passWord);

            UserEntity user = new UserEntity();

            user.setUserId(1029L);
            user.setUsername(userName);
            user.setPassword(newPass);
            user.setGender(0);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.YEAR, -17);
            calendar.add(Calendar.MONTH, -3);
            calendar.add(Calendar.WEEK_OF_MONTH, 1);
            calendar.add(Calendar.DAY_OF_WEEK, 3);
            Date time = calendar.getTime();

            user.setBirthday(time);

            calendar.add(Calendar.YEAR, -4);
            calendar.add(Calendar.MONTH, 2);
            calendar.add(Calendar.WEEK_OF_MONTH, 2);
            calendar.add(Calendar.DAY_OF_WEEK, -4);
            Date regist = calendar.getTime();

            user.setRegister_time(regist);
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -3);
            Date last = calendar.getTime();

            user.setLast_login_time(last);
            String clientIp = getClientIp();
            user.setLast_login_ip(clientIp);
            user.setUser_level_id(2);

            String nickName = jsonRequest.getString("nickName");
            String teleNumber = jsonRequest.getString("teleNumber");
            user.setNickname(nickName);
            user.setMobile(teleNumber);

            userService.save(user);
        }

        return toResponsSuccess("新用户注册成功！");
    }

    @PostMapping("/total")
    @ResponseBody
    public Object queryTotal(){
        return userService.queryTotal();
    }

    @PostMapping("/mobile")
    @ResponseBody
    public Object queryByMobile(){
        JSONObject jsonRequest = getJsonRequest();
        String mobile = jsonRequest.getString("mobile");

        UserEntity userVo = userService.queryByMobile(mobile);
        return toResponsSuccess(userVo);
    }

}
