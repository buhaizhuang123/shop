package com.bu.shop.sys.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2022/3/7 2:23 下午
 * @mark UserInfo
 */
@Data
public class UserInfo {


    private String id;

    private String userId;

    private String userName;

    private String userAddress;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date userBirDate;

    private Integer userAge;

    private Object userInfoData;

    public static void main(String[] args) {
        Function<Integer, ArrayList> list = ArrayList::new;
        ArrayList apply = list.apply(10);
        apply.add("200");
        apply.stream().forEach(System.out::println);
    }
}
