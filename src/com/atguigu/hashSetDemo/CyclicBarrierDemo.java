package com.atguigu.hashSetDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  CyclicBarrier 循环栅栏
 *
 *
 *
 * **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //需求: 32座校车固定停在校门口，需要等待32名学生才能发车，车门焊死，上了车就不允许下车只能等待人满开车。
        CyclicBarrier cyclicBarrier=new CyclicBarrier(32,()->{
            System.out.println("全部就位，来不及解释了快上车");
        });
        AtomicInteger count = new AtomicInteger(1);
        for (int i = 0; i < 32; i++) {
            new Thread(()->{
                try {

                    System.out.println("当前线程为:"+Thread.currentThread().getName() +"第"+count+"个学生上车了");
//                    int andIncrement = count.getAndIncrement();
//                    andIncrement=andIncre     ment+1;
//                    count.set(andIncrement);
                    count.set(count.getAndIncrement()+1);
                    cyclicBarrier.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
