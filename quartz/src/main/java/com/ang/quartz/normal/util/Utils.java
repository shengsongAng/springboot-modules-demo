package com.ang.quartz.normal.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @description: ${description}
 * @author: ssang
 * @create: 2021/5/11 0011 15:38
 **/
public class Utils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");

    /**
     * 通过输入指定日期时间生成cron表达式
     * @param dateTime
     * @return cron表达式
     */
    public static String getCron(LocalDateTime dateTime){
        String formatTimeStr = null;
        if (dateTime != null) {
            Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
            formatTimeStr = sdf.format(date);
        }

        return formatTimeStr;
    }
}
