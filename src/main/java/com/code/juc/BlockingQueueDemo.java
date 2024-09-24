package com.code.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lizhengqiang
 * @create 2024-09-24 18:11
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);

        //报异常组
        queue.add("a");
        queue.add("b");
        queue.add("c");

        queue.remove();
        queue.remove();
        queue.remove();

        //System.out.println(queue.element());

        //特殊值
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");

        queue.poll();
        queue.poll();
        queue.poll();

        queue.peek();

        //阻塞
        queue.put("a");
        queue.put("b");
        queue.put("c");

        queue.take();
        queue.take();
        queue.take();

        queue.offer("a",2L, TimeUnit.SECONDS);
        queue.offer("b",2L, TimeUnit.SECONDS);
        queue.offer("c",2L, TimeUnit.SECONDS);
        System.out.println(queue.offer("d", 2L, TimeUnit.SECONDS));

    }
}
