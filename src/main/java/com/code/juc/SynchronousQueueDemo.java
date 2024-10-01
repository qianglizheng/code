package com.code.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lizhengqiang
 * @create 2024-09-24 18:31
 **/
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<String>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t"+"put 1");
                queue.put("1");
                //put之后会阻塞在这里等待消费

                System.out.println(Thread.currentThread().getName()+"\t"+"put 2");
                queue.put("2");

                System.out.println(Thread.currentThread().getName()+"\t"+"put 3");
                queue.put("3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"\t"+queue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"\t"+queue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"\t"+queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
