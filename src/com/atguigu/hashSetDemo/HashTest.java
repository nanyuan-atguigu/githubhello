package com.atguigu.hashSetDemo;

import java.nio.channels.Pipe;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HashTest {
    private Lock lock=new ReentrantLock();
    private Condition cod1=lock.newCondition();
    private Condition cod2=lock.newCondition();

    private int twoChar=65;
    private int oneInt=1;
    private int num=1;
    public void oneThread(){
        lock.lock();
        try {
            while (num%2!=1){
                cod1.await();
            }
            for (int i = 0; i <2 ; i++) {
                System.out.print((oneInt++)+" ");
            }
            num++;
            cod2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
    public void twoThread(){
        lock.lock();
        try {
            while (num%2!=0){
                cod2.await();
            }
            System.out.print((char)twoChar++ +" ");
            num++;
            cod1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        HashTest hashTest=new HashTest();

        //0.两个线程打印12A34B
        new Thread(()->{
            for (int i = 0; i <26 ; i++) {
                hashTest.oneThread();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i <26 ; i++) {
                hashTest.twoThread();
                if(i==25){
                    System.out.println();
                    System.out.println("华丽分割线------------------------------------------------");
                    System.out.println("华丽分割线------------------------------------------------");
                    System.out.println("华丽分割线------------------------------------------------");
                }
            }
        },"BB").start();



        //1.证明set多线程不安全
//        Set set=new hashSet();
//
//
//        for (int i = 0; i <100; i++) {
//            new Thread(()->{
//                set.add(UUID.randomUUID().toString().substring(0,3));
//                System.out.println(set);
//            },"AA").start();
//
//        }
        //2.解决
        Set set=new CopyOnWriteArraySet();


        for (int i = 0; i <100; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,3));
                System.out.println(set);
            },"AA").start();

        }
        //3.证明map多线程不安全
        //Map<String,String> map=new HashMap<>();
        //4.解决
        Map<String,String> map=new ConcurrentHashMap<>();
        for (int i = 0; i <100; i++) {
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,3),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },"AA").start();

        }

    }
}
