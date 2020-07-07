package com.hlt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: houlintao
 * @Date:2020/7/7 下午2:39
 * @email 437547058@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/my")
public class JspController {

    @RequestMapping("/zhu")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("zhu");

        return modelAndView;
    }
}
