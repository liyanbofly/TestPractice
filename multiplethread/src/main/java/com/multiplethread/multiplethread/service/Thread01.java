package com.multiplethread.multiplethread.service;

import lombok.Data;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

public class Thread01 {

    @Data
    class  Person{
        private  Integer age;
    }


    static   class MyThread extends Thread{

         @Override
         public void  run(){
             System.out.println("我是继承Thread线程："+Thread.currentThread().getName());
         }
     }

    static class MyRunable implements Runnable{
         @Override
         public void run() {
             System.out.println("我是实现 Runable接口的-run方法："+Thread.currentThread().getName());
         }
     }

  static   class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("我是实现 Callable接口的-call："+Thread.currentThread().getName());
            return "it is call method";
        }
    }


    public static void main(String[] args) {



          new MyThread().start();
          new Thread(()->{
              System.out.println("我是lamda表达式开启线程");
          }).start();
       new Thread(new MyRunable()).start();



        try {
            ExecutorService executorService= Executors.newCachedThreadPool();
            Future<String> future02= executorService.submit(new MyCallable());
            System.out.println("future02.get() = " + future02.get());

            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
            Future<String> future = threadPoolExecutor.submit(new MyCallable());
            String result= future.get();
            System.out.println("result = " + result);

            FutureTask<String> futureTask= new FutureTask<String>( new MyCallable());
            Thread t1=new Thread(futureTask);
             t1.start();
            System.out.println("futureTask.get() = " + futureTask.get());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
