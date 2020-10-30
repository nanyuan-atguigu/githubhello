package com.atguigu.hashSetDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Target{
    public String getName() throws InterruptedException {

            TimeUnit.SECONDS.sleep(5);

        return Thread.currentThread().getName();

    }
}
public class hashWrite {

    public static void main(String[] args) {
        Target target= new Target();
        ReadWriteLock writeLock=new ReentrantReadWriteLock();
        new Thread(()->{
            try {
                String name = target.getName();
                writeLock.writeLock().lock();
                System.out.println(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
        new Thread(()->{
            try {
                String name = target.getName();
                writeLock.writeLock().lock();
                System.out.println(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();
    }
}
