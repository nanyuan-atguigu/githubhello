package com.atguigu.hashSetDemo;

public class mainTest {
    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                String name = Thread.currentThread().getName();
                System.out.println(name);
            },String.valueOf(i)).start();

        }
        System.out.println("dev");
        System.out.println(5);
    }

}
