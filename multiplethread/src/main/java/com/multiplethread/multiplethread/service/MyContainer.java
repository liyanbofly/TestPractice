package com.multiplethread.multiplethread.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 生产者 10个后等待
 * 消费者 消费
 *
 * put: 放任务等到10个任务时 阻塞地
 * take：取任务，取出来后通知 生产都继续生产
 */
public class MyContainer<T> {

 //存储数据当作队列
 public List<T> listStr=new ArrayList<>();
 // 最大容量
 public int  MAX_SIZE=10;
 //用于阻塞线程 wait notify
 public Object lockWait=new Object();
    private int count = 0;

/**
 * 阻塞 添加元素
 * @param element
 */
 public  synchronized void  put(T element){
     synchronized(lockWait) {
         while (listStr.size() == 10) {
             try {
                 lockWait.wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
                 System.out.println("product-error");
             }
         }
         listStr.add(element);
         ++count;
         lockWait.notifyAll();
     }
 }

 public  T get(){
     synchronized(lockWait){
         T t = null;
         while (listStr.size()==0){
             try {
                 lockWait.wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
                 System.out.println("consumer-error");
             }
         }

         // 取出并移出
         t = listStr.get(0);
         listStr.remove(0);
         count --;
         lockWait.notifyAll(); //通知生产者进行生产
         return t;
     }


 }


    public static void main(String[] args) {
        MyContainer<String> c = new MyContainer<>();
        //启动消费者线程
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++) System.out.println(c.get());
            }, "c" + i).start();
        }

        Queue<String> aa=new ArrayBlockingQueue<String>(10);
        aa.poll();
        aa.offer("liyanbo");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0; i<2; i++) {
            new Thread(()->{
                for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }





}
