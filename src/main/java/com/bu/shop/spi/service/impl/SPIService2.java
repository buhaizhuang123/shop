package com.bu.shop.spi.service.impl;

import com.bu.shop.spi.service.SPIService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author haizhuangbu
 * @date 2022/3/7 5:49 下午
 * @mark SPIService1
 */
public class SPIService2 implements SPIService {
    @Override
    public void execute() {
        System.out.println("SPI2");
    }


    public static void main(String[] args) {


        try {
            Class<?> aClass = Class.forName("com.bu.shop.spi.service.SPIService");
            String name = aClass.getName();
            System.out.println(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
        Iterator<SPIService> iterator = load.iterator();
        while (iterator.hasNext()){
            SPIService next = iterator.next();
            next.execute();
        }
    }
}
