package com.bu.shop.sys.service;

import com.bu.shop.sys.dto.UserInfo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/3/7 2:29 下午
 * @mark UserInfoService
 */
public interface UserInfoService {


    List<UserInfo> findAllUserInfo();

}
