package com.code.juc;

import java.util.concurrent.Semaphore;

/**
 * @author lizhengqiang
 * @create 2024-09-23 21:15
 * 控制一次性最多有多少个线程
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t得到令牌");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "\t释放令牌");
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
