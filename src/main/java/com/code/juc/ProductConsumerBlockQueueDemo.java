package com.code.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lizhengqiang
 * @create 2024-09-24 20:02
 **/
public class ProductConsumerBlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ShareDataCopy data = new ShareDataCopy(new ArrayBlockingQueue<>(3));

        new Thread(() -> {
            try {
                data.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"A").start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                data.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"B").start();

        TimeUnit.SECONDS.sleep(10);
        data.stop();

    }
}

class ShareDataCopy {
    private volatile boolean flag = true;

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    BlockingQueue<String> queue;

    public ShareDataCopy(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void produce() throws InterruptedException {
        while(flag){
            boolean res = queue.offer(ATOMIC_INTEGER.incrementAndGet() + "");
            if(res){
                System.out.println("插入队列成功" + ATOMIC_INTEGER.get());
            } else {
                System.out.println("插入队列失败" + ATOMIC_INTEGER.get());
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void consume() throws InterruptedException {
        while(flag){
            String res = queue.poll(2, TimeUnit.SECONDS);
            if(res == null || "".equalsIgnoreCase(res)){
                flag = false;
                System.out.println("消费失败" + ATOMIC_INTEGER.get());
                return;
            }
            System.out.println("消费成功" + ATOMIC_INTEGER.get());
            TimeUnit.SECONDS.sleep(5);
        }
    }

    public void stop(){
        flag = false;
    }
}
