package com.code.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lizhengqiang
 * @create 2024-09-25 20:13
 **/
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //固定个线程
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);

        //一个线程
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        //根据情况自动添加线程
        ExecutorService executorService = Executors.newCachedThreadPool();

        try  {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        }finally {
            executorService.shutdown();
        }
    }
}
