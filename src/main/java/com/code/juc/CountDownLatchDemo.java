package com.code.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author lizhengqiang
 * @create 2024-09-23 20:07
 * 让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒
 * 初始值是自定义 减到0就结束
 * 做减法
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
                //计数器减一
                countDownLatch.countDown();
            }).start();
        }

        //等待计数器为0
        countDownLatch.await();

        System.out.println("线程全部执行完毕");
    }
}
