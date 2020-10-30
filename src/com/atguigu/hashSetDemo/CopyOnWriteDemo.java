package com.atguigu.hashSetDemo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CopyOnWriteDemo {
    private ReentrantLock lock=new ReentrantLock();
    private transient volatile Object[] array;
    final Object[] getArray() {
        return array;
    }
    final void setArray(Object[] a) {

        array = a;
    }
    public static void main(String[] args) {
        //CopyOnWriteArrayList copy=new CopyOnWriteArrayList();
        ArrayList arrayList=new ArrayList();

    }
    public <E> boolean add(E e){
        final ReentrantLock lock=this.lock;
        lock.lock();
        try {
            Object[] oldArray = getArray();
            int len = oldArray.length;
            Object[] newArray = Arrays.copyOf(oldArray, len+1);
            newArray[len] = e;
            setArray(newArray);
            return true;
        }finally {
            lock.unlock();
        }


    }

}
