package com.atguigu.hashSetDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private ReadWriteLock lock=new ReentrantReadWriteLock();
    private Map<String,Object> map=new HashMap<>();
    public void put(String key,String value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写了！！！！");
            map.put(key, value);
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName()+"写操作结束");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();

        }
    }
    public void get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读了！！！");
            Object value=map.get(key);
            Thread.sleep(300);
            System.out.println(value);
            System.out.println(Thread.currentThread().getName()+"读操作结束！！！");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

}
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache=new MyCache();


    }
}
