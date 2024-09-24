package com.code.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lizhengqiang
 * @create 2024-09-23 20:46
 * 做加法(底层还是减法 --count),与CountDownLatch的区别为CyclicBarrier可以重置 reset,即可以循环使用
 * 如果收集到14颗龙珠可以召唤两次神龙，不用反复创建对象，使用CountDownLatch用完一次之后
 * 还要使用需要创建新的CountDownLatch
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });

        for (int i = 0; i < 7; i++) {
            int j = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 收集到:" + j + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

    }
}
