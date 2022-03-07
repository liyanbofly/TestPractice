package com.multiplethread.multiplethread.service;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CountdownLanchAndJoinTest {

    public static void main(String[] args) {

        // CountDownLanch 使用
//        useCountDownLanch();

        // 使用Join 阻塞
        useThreadJoin();


    }

    /**
     * CountDownLanch 使用
     */
    private static void  useCountDownLanch(){

        Thread [] threads=new Thread[10];
        CountDownLatch  countDownLatch=new CountDownLatch(threads.length);

        for (int i = 0; i <threads.length ; i++) {

            threads[i]=new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+",当前I："+new Date());
                    Thread.sleep(1000);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        for (Thread singleThr :threads) {
            singleThr.start();

        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("wo shi zhu xiancheng:"+Thread.currentThread().getName()+",时间："+new Date());


    }

    /**
     * Join 使用
     */
    private static void  useThreadJoin(){

        Thread [] threads2=new Thread[10];
        for (int i = 0; i <threads2.length ; i++) {
            threads2[i]=new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+",当前I："+new Date());
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        for (Thread singleThr :threads2) {
            singleThr.start();
        }

        for (Thread singleThr :threads2) {
            try {
                singleThr.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("wo shi zhu xiancheng--使用了join:"+Thread.currentThread().getName()+",时间："+new Date());


    }


}
