package com.atguigu.hashSetDemo;

import java.util.concurrent.TimeUnit;

public class Phone {
        public static   synchronized void sendSMS() throws Exception
        {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("------sendSMS");
        }
        public static synchronized void sendEmail() throws Exception
        {
            System.out.println("------sendEmail");
        }

        public void getHello()
        {
            System.out.println("------getHello");
        }



    public static void main(String[] args) throws InterruptedException {
        Phone ph1=new Phone();
        Phone ph2=new Phone();
        new Thread(()->{
            try {
                ph1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            try {
                ph2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();
    }

}
