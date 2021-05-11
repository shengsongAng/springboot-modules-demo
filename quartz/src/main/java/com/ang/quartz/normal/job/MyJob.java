package com.ang.quartz.normal.job;

import com.ang.quartz.normal.pojo.Data;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @description: ${description}
 * @author: ssang
 * @create: 2021/5/11 0011 15:30
 **/
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Data d = (Data) jobExecutionContext.getJobDetail().getJobDataMap().get("param");
        System.out.println(String.format("定时任务启动：%s，并获取参数id：%s，flag：%s",
                jobExecutionContext.getJobDetail().getKey(),d.getId(),d.getFlag()));
    }
}
