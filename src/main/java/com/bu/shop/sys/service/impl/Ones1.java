package com.bu.shop.sys.service.impl;

import com.sun.jmx.remote.internal.ArrayQueue;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @author haizhuangbu
 * @date 2022/3/14 10:21 上午
 * @mark Ones1
 */
public class Ones1 {

   private CountDownLatch countDownLatch = new CountDownLatch(2);



    @Test
   public void ts() throws ExecutionException, InterruptedException {
        ArrayBlockingQueue arrayQueue = new ArrayBlockingQueue<Runnable>(2,true);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,200L, TimeUnit.DAYS,arrayQueue);
        Future<?> submit = threadPoolExecutor.submit(() -> {
            try {
                System.out.println("123");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                countDownLatch.countDown();
            }
        });

        Object o = submit.get();


//        BeanUtils.copyProperties();

    }

}
