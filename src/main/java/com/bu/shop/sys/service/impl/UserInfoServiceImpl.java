package com.bu.shop.sys.service.impl;

import com.bu.shop.sys.dao.UserInfoMapper;
import com.bu.shop.sys.dto.UserInfo;
import com.bu.shop.sys.service.UserInfoService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/3/7 2:30 下午
 * @mark UserInfoServiceImpl
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public List<UserInfo> findAllUserInfo() {
        RowBounds rowBounds = new RowBounds(0,1);
        return userInfoMapper.findAll(rowBounds);
    }
}
