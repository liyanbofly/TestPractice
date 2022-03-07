package com.multiplethread.multiplethread.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadRentantLock {


    private  static  Object oLock=new Object();
    public static void main(String[] args) {

        ReentrantLock reentrantLock=new ReentrantLock();

        Thread th1=new Thread(()->{
            try {
                if(reentrantLock.tryLock(5, TimeUnit.SECONDS)){
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                    Thread.sleep(5000);
                }else{
                    System.out.println("没有获取到锁-tryLock测试");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread th2=new Thread(()->{
            try {

                if(reentrantLock.tryLock(5, TimeUnit.SECONDS)){
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                    Thread.sleep(5000);
                }else{
                    System.out.println("线程："+Thread.currentThread().getName()+" 没有获取到锁-tryLock测试");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        th1.start();
        th2.start();




    }
}
