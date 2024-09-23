package com.code.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lizhengqiang
 * @create 2024-09-23 19:40
 * 读写锁：
 * 允许多个线程同时读，但是只允许一个线程写，在线程获取到写锁的时候，其他写操作和读操作都会处于阻塞状态；
 * 读锁和写锁也是互斥的，所以在读的时候是不允许写的
 **/
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            int j = i;
            new Thread(() -> {
                try {
                    myCache.get(String.valueOf(j));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            int j = i;
            new Thread(() -> {
                try {
                    myCache.put(String.valueOf(j),j);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}

/**
 * 资源类
 * 读，写
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) throws InterruptedException {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            TimeUnit.SECONDS.sleep(2);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成：");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) throws InterruptedException {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取：" + key);
            TimeUnit.SECONDS.sleep(2);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成：");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }
}
