package com.code.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lizhengqiang
 * @create 2024-09-24 20:22
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        //两个相同的futureTask只会执行一次
        Thread t1 = new Thread(futureTask);
        t1.start();

        Thread t2 = new Thread(futureTask);
        t2.start();

        int a = futureTask.get();
        int b = 2;
        System.out.println(a + b);
    }
}

class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("once");
        return 1;
    }
}
