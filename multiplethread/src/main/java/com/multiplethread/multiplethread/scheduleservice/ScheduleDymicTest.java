package com.multiplethread.multiplethread.scheduleservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 *
 *  @Schedule注解的一个缺点就是其定时时间不能动态更改，它适用于具有固定任务周期的任务，若要修改任务执行周期
 *  只能走“停服务→修改任务执行周期→重启服务”这条路。
 *
 * 而基于 SchedulingConfigurer 接口方式可以做到。SchedulingConfigurer 接口可以实现在@Configuration 类上
 * ，同时不要忘了，还需要@EnableScheduling 注解的支持。
 *
 */
@Component
@SuppressWarnings("all")
@Slf4j
@EnableScheduling
@Configuration
public class ScheduleDymicTest implements SchedulingConfigurer {
    @Autowired
    TaskDataService taskDataSerivce;
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> doScheduler(),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = taskDataSerivce.getCorn();
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }

                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );

    }
    //判断定时任务是否已关闭
    public void doScheduler(){
        boolean isswitch = taskDataSerivce.getSwitch();
        if(isswitch) {
            processTask();
        }else{
            log.info("ScheduleDymicTest-定时任务已关闭！");
        }
    }


    /**
     * 处理定时任务逻辑
     */
    public  void processTask(){
        System.out.println("我是任务逻辑处理方法");
    }







}
