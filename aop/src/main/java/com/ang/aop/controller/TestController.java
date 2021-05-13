package com.ang.aop.controller;

import com.ang.aop.annotation.OptLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: ${description}
 * @author: ssang
 * @create: 2021/5/13 0013 10:47
 **/
@RestController
public class TestController {
    /**
     * 测试注解的切点
     * @param id
     * @return
     */
    @OptLog(optType = "测试类型",optModule = "测试模块",optDesc = "测试方法")
    @RequestMapping("/test1")
    public String test1(@RequestParam("id") String id){
        System.out.println("test1，id:"+id);
        return "test1";
    }

    /**
     * 测试controller下的切点
     * @return
     */
    @RequestMapping("test2")
    public String test2(){
        System.out.println("test2");
        return "test2";
    }

    /**
     * 测试捕获异常类型通知方式的切点
     * @return
     */
    @RequestMapping("test3")
    public String test3(){
        //创建异常代码，进入异常通知
        int i = 1 / 0;
        return "test3";
    }
}
