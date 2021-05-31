package com.ang.quartz.normal.job;

import com.ang.quartz.normal.pojo.Data;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: ${description}
 * @author: ssang
 * @create: 2021/5/11 0011 15:30
 **/
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("==================="+sdf.format(new Date()));

        Data d = (Data) jobExecutionContext.getJobDetail().getJobDataMap().get("param");
        System.out.println(String.format("定时任务启动：%s，并获取参数id：%s，flag：%s",
                jobExecutionContext.getJobDetail().getKey(),d.getId(),d.getFlag()));

        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().getString("job_param"));
//        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().getInt(""));
    }
}
