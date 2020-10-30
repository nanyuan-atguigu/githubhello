package com.atguigu.hashSetDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TargetA{
    Lock lock=new ReentrantLock();
    Lock lock2=new ReentrantLock();
     void getA()  {
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"获得A锁");
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println(Thread.currentThread().getName()+"试图抢占B锁");
            getB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("A锁关闭");
        }

    }
    void getB()  {
        lock2.lock();
        System.out.println(Thread.currentThread().getName()+"获得B锁");
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println(Thread.currentThread().getName()+"试图抢占A锁");
            getA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
            System.out.println("B锁关闭");
        }
    }
}
public class DeathLock {

    public static void main(String[] args) {

        TargetA target=new TargetA();
        //jps
        //jstack
       new Thread(()->{
           target.getA();
       },"AA").start();
        new Thread(()->{
            target.getB();
        },"BB").start();
        Executors.newFixedThreadPool(5);
    }
}
