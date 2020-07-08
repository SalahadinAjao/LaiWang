package com.hlt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlt.annotations.CurrentLoginUser;
import com.hlt.annotations.SkipAuth;
import com.hlt.entity.UserEntity;
import com.hlt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: houlintao
 * @Date:2020/7/7 下午5:23
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/token")
public class TokenController extends BaseController {
    @Autowired
    private TokenService tokenService;

    @SkipAuth
    @PostMapping("/create")
    public Object createToken(){

        JSONObject jsonRequest = getJsonRequest();

        Long userId = jsonRequest.getLong("userId");
        Map<String, Object> token = tokenService.createToken(userId);

        return toResponsSuccess(token);
    }
}
