package com.atguigu.hashSetDemo;

import java.util.concurrent.CountDownLatch;

/**
 *
 * CountDownLatch 减少记数
 *
 *
 * **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //需求:  100个人捉迷藏 main线程需要等 100个人都藏好，开始抓人 sout开始抓人即可
        CountDownLatch countDownLatch=new CountDownLatch(100);
        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                countDownLatch.countDown();
                System.out.println("线程:"+Thread.currentThread().getName()+",第"+(Integer.valueOf(Thread.currentThread().getName())+1) +"个人 藏好了！");
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("main线程开始抓人");
     }
}
