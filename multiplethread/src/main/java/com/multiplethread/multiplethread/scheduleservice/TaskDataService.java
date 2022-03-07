package com.multiplethread.multiplethread.scheduleservice;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class TaskDataService {

    /**
     * 执行频率 及每隔多少分钟执行一次
     * @return
     */
    public  String getCorn(){
        // 可从数据中获取信息
        return "0/5 * * * * ?";
    }

    /**
     * 定时任务开关
     * @return
     */
    public  Boolean getSwitch(){
        // 可从数据库获取
        return  true;
    }

    /**
     * 定时任务名称
     * @return
     */
    public  String getTaskName(){
        return  "taskNameFirst";
    }



}
