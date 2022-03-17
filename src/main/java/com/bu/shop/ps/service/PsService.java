package com.bu.shop.ps.service;

import com.bu.shop.ps.Dto.PersonInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author haizhuangbu
 * @date 2022/3/14 2:16 下午
 * @mark PsService
 */
public interface PsService {

    PersonInfo findPsInfo(String id);

    void addPsInfo(PersonInfo personInfo);

    void deleteById(String id) throws ExecutionException, InterruptedException;

    List<PersonInfo> findAllInfo();

}
