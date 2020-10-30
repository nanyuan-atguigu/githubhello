package com.atguigu.hashSetDemo;

import java.util.concurrent.*;

class MyThreadRun implements Runnable{

    @Override
    public void run() {
        System.out.println("MyThreadRun implements Runnable  runnable");
    }
}
//class MyThreadCall implements Callable<Integer> {
//
//    @Override
//    public Integer call() throws Exception {
//        System.out.println("MyThreadCall implements Callable<Integer>   callable");
//        return 200;
//    }
//}
public class FourTest {


    public static void main(String[] args) {
        System.out.println(160+1024+2048);
        FutureTask<Integer> futureTask1=new FutureTask(()->{
            System.out.println(Thread.currentThread().getName()+"futureTask1");
            //模拟计算所需时间
            TimeUnit.SECONDS.sleep(1);

            return 1024;
        });
        FutureTask<Integer> futureTask2=new FutureTask(()->{
            System.out.println(Thread.currentThread().getName()+"futureTask2");
            //模拟计算所需时间
            TimeUnit.SECONDS.sleep(2);
            return 2048;
        });
        new Thread(futureTask1,"AA").start();
        new Thread(futureTask2,"BB").start();
        Integer a=10+10;
        System.out.println("计算: "+a);
        Integer b=20+20;
        System.out.println("计算: "+b);
        Integer c=20+20;
        System.out.println("计算: "+c);
        Integer d=30+30;
        System.out.println("计算: "+d);
        Integer result=a+b+c+d;
        while (!futureTask1.isDone()){
            //System.out.println("wait 1 ing ********************");
        }
        try {
            Integer f1 = (Integer) futureTask1.get();
            System.out.println("wait1结束  result 与f1计算 相加");
            result=result+f1;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        while (!futureTask2.isDone()){
            //System.out.println("wait 2 ing ********************");
        }
        try {
            Integer f2 = (Integer) futureTask2.get();
            System.out.println("wait2结束  result 与f2计算 相加");
            result=result+f2;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("最终结果为:"+result);



    }
}
