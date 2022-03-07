package com.multiplethread.multiplethread.service;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

/**
 * FutureTask -使用方式
 * 开启一个线异步线程，并让主线程等待返回结果
 */
public class FutureTaskSevice {

    public static void main(String[] args) {


        FutureTask<String> futureTask=new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // todo
                int count=0;
                for(int i=0;i<10;i++){
                    count=count+i;
                }
                return "it is subThread complete-count:"+count;
            }
        });

        // 通过线程执行
        Thread thread=new Thread(futureTask);
        thread.start();
        try {
            String resultStr = futureTask.get();
            System.out.println("resultStr = " + resultStr);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("I am is main Thread");


        //只能存放一个
        // SynchronousQueue<String> synchronousQueue=new SynchronousQueue<>();



        /**
         *
         * 自定义线程池：使用原因 1、定义 workQueue:设置队列容量  不会让任务堆集过多：影响任务执行时间或造成oom
         *     2、自定ThreadFactory：可以定义线程名称便于遇到问题排查
         *     总结：任务进来 如果核心线程数未达到会首先创建核心线程执行任务
         *         当核心线程数到达，会放进workQueue中当workQueue 放满会创建  大于核心线程数小于最大线程数那部分线程         *
         *
         *
         * workQueue：可以自定义容量 常用 ArrayBlockingQueue; Excutors.newFixedThreadPool等生出来的线程队列 LinkedBlockingQueue 容量为Integer.maxVaule
         * ThreadFactory：可以定义线程名称便于遇到问题排查
         *
         * RejectedExecutionHandler handler：拒绝策略  可自定义将 不能执行的任务记录，然后做补尝机制处理
         *      1.ThreadPoolExecutor.AbortPolicy：线程池的默认拒绝策略为AbortPolicy，即丢弃任务并抛出RejectedExecutionException异常
         *
         *      2.ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常。如果线程队列已满，则后续提交的任务都会被丢弃，且是静默丢弃
         *
         *      3.ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务
         *
         *      4.ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务（不会丢弃任务，最后所有的任务都执行了，并不会抛出异常）
         */

         ThreadPoolExecutor threadPool=new ThreadPoolExecutor(5,10,30,TimeUnit.SECONDS
                 ,new ArrayBlockingQueue<>(20),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());// 也可以自定义线程池
        Future<Integer> future=  threadPool.submit(()->{
            int sumInt=0;
            for (int i = 0; i < 10; i++) {
                sumInt=sumInt+i;
            }
            return sumInt;
        });
        try {
            future.get();//阻塞等待
        }   catch (Exception e) {
            e.printStackTrace();
        }


        ExecutorService executorService=Executors.newFixedThreadPool(5);

        Future<String> futureStr= executorService.submit(()->{
            return "it is result";
        });
        try {
            System.out.println("futureStr.get() = " + futureStr.get());// 此处会阻塞等待
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("I am is main Thread-2");



    }
}
