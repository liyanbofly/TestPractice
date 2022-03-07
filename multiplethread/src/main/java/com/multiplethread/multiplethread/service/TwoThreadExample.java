package com.multiplethread.multiplethread.service;

import lombok.Synchronized;

import java.util.concurrent.CountDownLatch;

public class TwoThreadExample {

    public static void main(String[] args) {
        String [] letters={"A","B","C","D","E"};
        String [] numbers={"1","2","3","4","5"};

        final  Object lockO=new Object();
        CountDownLatch countDownLatch=new CountDownLatch(1);

        try {
            Thread th1=new Thread(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockO){
                    System.out.println("2");
                    for (String singlel:letters ) {
                        System.out.println("singlel = " + singlel);
                        try {
                            lockO.notify();
                            lockO.wait(); // 让出锁

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    lockO.notify();
                }


            });
            Thread th2=new Thread(()->{
                synchronized (lockO){
                    System.out.println("1");
                    countDownLatch.countDown();
                    for (String singlen:numbers ) {
                        System.out.println("singlen = " + singlen);
                        lockO.notify();
                        try {
                            lockO.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }



                    }
                    lockO.notify();
                }


            });
th1.start();

th2.start();

            System.out.println("it is main ");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
