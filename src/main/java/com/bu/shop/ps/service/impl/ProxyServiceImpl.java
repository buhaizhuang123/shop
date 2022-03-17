package com.bu.shop.ps.service.impl;

import com.bu.shop.ps.service.ProxyService;

/**
 * @author haizhuangbu
 * @date 2022/3/17 8:56 下午
 * @mark ProxyServiceImpl
 */
public class ProxyServiceImpl implements ProxyService {
    @Override
    public String getStr(String info) {
        System.out.println("123");
        return "123";
    }
}
