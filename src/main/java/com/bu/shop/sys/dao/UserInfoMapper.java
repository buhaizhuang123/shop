package com.bu.shop.sys.dao;

import com.bu.shop.sys.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/3/7 2:23 下午
 * @mark UserInfoMapper
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 查询所有
     */
    List<UserInfo> findAll(RowBounds rowBounds);

}
