package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.CurrentLoginUser;
import com.hlt.annotations.SkipAuth;
import com.hlt.entity.UserEntity;
import com.hlt.service.UserService;
import com.hlt.utils.MD5;
import org.apache.commons.codec.digest.DigestUtils;
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

    @SkipAuth
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

            String newPass = DigestUtils.sha256Hex(passWord);

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
    public Object queryByMobile(@CurrentLoginUser UserEntity loginUser){
       /* JSONObject jsonRequest = getJsonRequest();
        String mobile = jsonRequest.getString("mobile");*/

        String mobile = loginUser.getMobile();

        UserEntity userVo = userService.queryByMobile(mobile);
        return toResponsSuccess(userVo);
    }

    @PostMapping("/updatemobile")
    public Object updateUserMobile(@CurrentLoginUser UserEntity loginUser){
        JSONObject jsonRequest = getJsonRequest();
        String mobile = jsonRequest.getString("mobile");
        Long userId = loginUser.getUserId();

        UserEntity userEntity = userService.queryObject(userId);

        userEntity.setMobile(mobile);

        userService.update(userEntity);

        return toResponsMsgSuccess("手机号更新成功");
    }

    @PostMapping("/updatepass")
    public Object updatePassWord(@CurrentLoginUser UserEntity loginUser){
        JSONObject jsonRequest = getJsonRequest();
        String password = jsonRequest.getString("password");

        String sha256Hex = DigestUtils.sha256Hex(password);

        UserEntity userEntity = userService.queryObject(loginUser.getUserId());

        userEntity.setPassword(sha256Hex);

        userService.updatePassword(userEntity);

        return toResponsMsgSuccess("密码更新成功");
    }

}
