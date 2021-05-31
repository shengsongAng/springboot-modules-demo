package com.ang.quartz.normal.test;

import com.ang.quartz.normal.job.MyJob;
import com.ang.quartz.normal.pojo.Data;
import com.ang.quartz.normal.util.Utils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: ${description}
 * @author: ssang
 * @create: 2021/5/11 0011 15:33
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        addJob();
        Thread.sleep(9000);
        System.out.println("修改触发时间============="+sdf.format(new Date()));
        updateJobTime();
    }

    //添加一个定时任务
    public static void addJob() throws Exception{
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 创建MyJob的JobDetail实例，并设置name/group
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob","myJobGroup")
                .usingJobData("job_param","job_param_val")
                .build();
        //给jobDetail传递参数
        Data data = new Data("1","我是参数");
        jobDetail.getJobDataMap().put("param",data);

        // 创建Trigger触发器设置使用cronSchedule方式调度
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","myTriggerGroup")
                .usingJobData("job_trigger_param","job_trigger_param_val")
                .startNow()
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                //.withSchedule(CronScheduleBuilder.cronSchedule(Utils.getCron(LocalDateTime.of(2021,5,31,14,41,00))))
                .withSchedule(CronScheduleBuilder.cronSchedule(Utils.getCronNow()))
                .build();

        // 注册JobDetail实例到scheduler以及使用对应的Trigger触发时机
        scheduler.scheduleJob(jobDetail,trigger);

        // 启动
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    //移除定时任务方法
    public static void removeJob() throws Exception{
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobKey jobKey = JobKey.jobKey("myJob","myJobGroup");
        TriggerKey triggerKey = TriggerKey.triggerKey("myTrigger","myTriggerGroup");

        scheduler.pauseTrigger(triggerKey);// 停止触发器
        scheduler.unscheduleJob(triggerKey);// 移除触发器
        scheduler.deleteJob(jobKey);//移除定时任务
    }

    //修改定时任务时间
    public static void updateJobTime() throws Exception{
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        TriggerKey triggerKey = TriggerKey.triggerKey("myTrigger","myTriggerGroup");
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        // 触发器名,触发器组
        triggerBuilder.withIdentity("myTrigger", "myTriggerGroup");
        triggerBuilder.startNow();
        // 触发器时间设定
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(Utils.getCronNow()));
        // 创建Trigger对象
        trigger = (CronTrigger) triggerBuilder.build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }
}
