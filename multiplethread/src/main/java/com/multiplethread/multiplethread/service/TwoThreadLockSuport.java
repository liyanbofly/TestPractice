package com.multiplethread.multiplethread.service;

import java.util.concurrent.locks.LockSupport;

public class TwoThreadLockSuport {

     static Thread th1=null,th2=null;
    public static void main(String[] args) {
        String[] letters = {"A", "B", "C", "D", "E"};
        String[] numbers = {"1", "2", "3", "4", "5"};

        LockSupport.park();
        System.out.println("park");
        LockSupport.unpark(Thread.currentThread());


        try {
              th1 = new Thread(() -> {
                for (String singlel : letters) {
                    System.out.println("singlel = " + singlel);
                    LockSupport.unpark(th2);
                    LockSupport.park();
                }
            });
              th2 = new Thread(() -> {

                for (String singlen : numbers) {
                    LockSupport.park();
                    System.out.println("singlen = " + singlen);
                    LockSupport.unpark(th1);
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
