package com.bu.shop.ps.Dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author haizhuangbu
 * @date 2022/3/14 2:18 下午
 * @mark PersonInfo
 */
@Data
@Accessors(chain = true)
public class PersonInfo {

    private String id;

    private String sex;

    private String name;

    private String context;

}
