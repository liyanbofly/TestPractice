package com.multiplethread.multiplethread.scheduleservice;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 定时任务
 */
@Component
@EnableScheduling
public class ScheduleTest {

    /**
     * 1分钟执行一次 fixedRate:每隔几分钟执行一次--1分钟;initialDelay：第一次执行延时 10秒
     *  * @description：基于注解(@Scheduled)的简单定时器demo
     *  *
     *  * cron表达式语法:[秒] [分] [小时] [日] [月] [周] [年]
     *  * @Scheduled(fixedDelay = 5000) //上一次执行完毕时间点之后5秒再执行
     *  * @Scheduled(fixedDelayString = "5000") //上一次执行完毕时间点之后5秒再执行
     *  * @Scheduled(fixedRate = 5000) //上一次开始执行时间点之后5秒再执行
     *  * @Scheduled(initialDelay=1000, fixedRate=5000) //第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     *  *

     */
    @Scheduled(fixedRate =60 * 1000, initialDelay = 10 * 1000)
    public void oneMinuteTask() {
        try {
            System.out.println("\"成功执行\" = " + "成功执行;时间"+ LocalDateTime.now());
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.out.println("it is error");
        }
    }



}
