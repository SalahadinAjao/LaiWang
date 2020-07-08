package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.SkipAuth;
import com.hlt.service.TokenService;
import com.hlt.service.UserService;
import com.hlt.utils.ReturnObj;
import com.hlt.validators.Assert;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/8 下午5:50
 * @email 437547058@qq.com
 * @Version 1.0
 * api接口登录授权
 */

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

   @SkipAuth
   @PostMapping("/login")
   public Object login(){
       JSONObject jsonRequest = getJsonRequest();
       String mobile = jsonRequest.getString("mobile");
       String password = jsonRequest.getString("password");
       Assert.isBlank(mobile, "手机号不能为空");
       Assert.isBlank(password, "密码不能为空");

       long userId = userService.login(mobile, password);
       //通过userId生成token
       Map<String, Object> token = tokenService.createToken(userId);
       return toResponsObject(400,"登录成功",token);
   }

}
