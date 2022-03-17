package com.bu.shop.sys.controller;

import com.bu.shop.sys.dto.UserInfo;
import com.bu.shop.sys.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/3/7 2:19 下午
 * @mark SysController
 */
@RestController
@RequestMapping("sys")
public class SysController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("login")
    public String login(){
        return "登录成功";
    }

    @RequestMapping("all")
    public List<UserInfo> findAll(){
        return userInfoService.findAllUserInfo();
    }

}
