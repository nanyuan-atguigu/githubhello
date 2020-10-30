package com.atguigu.hashSetDemo;

import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(5);

        for (int i = 0; i <10 ; i++) {
            FutureTask task=new FutureTask(()->{

                semaphore.acquire();
                System.out.println("线程："+Thread.currentThread().getName()+"来了");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("线程："+Thread.currentThread().getName()+"告辞了");
                semaphore.release();
                return 66;
            });
            new Thread(task,String.valueOf(i)).start();
        }
    }
}
