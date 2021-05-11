package com.ang.quartz.normal.test;

import com.ang.quartz.normal.job.MyJob;
import com.ang.quartz.normal.pojo.Data;
import com.ang.quartz.normal.util.Utils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;

/**
 * @description: ${description}
 * @author: ssang
 * @create: 2021/5/11 0011 15:33
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 启动scheduler
        scheduler.start();

        // 创建MyJob的JobDetail实例，并设置name/group
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob","myJobGroup1")
                .usingJobData("job_param","job_param1")
                .build();
        //给jobDetail传递参数
        Data data = new Data("1","我是参数");
        jobDetail.getJobDataMap().put("param",data);

        // 创建Trigger触发器设置使用cronSchedule方式调度
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","myTriggerGroup1")
                .usingJobData("job_trigger_param","job_trigger_param1")
                .startNow()
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .withSchedule(CronScheduleBuilder.cronSchedule(Utils.getCron(LocalDateTime.of(2021,5,11,15,59,20))))
                .build();

        // 注册JobDetail实例到scheduler以及使用对应的Trigger触发时机
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
