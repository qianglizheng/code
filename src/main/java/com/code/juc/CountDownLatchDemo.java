package com.code.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author lizhengqiang
 * @create 2024-09-23 20:07
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("线程全部执行完毕");
    }
}
