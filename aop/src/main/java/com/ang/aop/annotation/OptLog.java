package com.ang.aop.annotation;

import java.lang.annotation.*;

@Documented//定义一个注解
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
public @interface OptLog {
    String optModule() default ""; // 操作模块
    String optType() default "";  // 操作类型
    String optDesc() default "";  // 操作说明
}
