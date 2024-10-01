package com.code.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lizhengqiang
 * @create 2024-09-24 19:27
 **/
public class ProductConsumerTradition {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            try {
                shareData.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                shareData.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

class ShareData {
    private int num;

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        try {
            lock.lock();
            while (num != 0){
                condition.await();
            }
            System.out.println("生产------"+ ++num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        try {
            lock.lock();
            while (num == 0){
                condition.await();
            }
            System.out.println("消费------"+ --num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
