package com.multiplethread.multiplethread.service;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TwoThreadTest {
    @Data
    static class TestList{
       public   List<String> listD=new ArrayList<>();
        public  synchronized void  add(String el){
            listD.add(el);
        }
        public synchronized int size(){
            return listD.size();
        }
    }

    public static void main(String[] args) {
        TestList cl=new TestList();


        Person p=new Person();
        Collection<String> list1 =  Collections.synchronizedCollection(new ArrayList<>());
        list1.add("it is success");
        System.out.println("listD2 = " + list1);

        CountDownLatch latch1=new CountDownLatch(1);
        CountDownLatch latch2=new CountDownLatch(1);

        Thread th2=new Thread(()->{
            try {
                latch1.await();
                latch2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am  th2");

        },"th2");
        th2.start();

        Thread th1=new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                cl.add(String.valueOf(i));
                System.out.println("我是线程1i:"+i);
                if(cl.size()==5){
                    latch1.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }

        },"th1");

        th1.start();






    }


}
