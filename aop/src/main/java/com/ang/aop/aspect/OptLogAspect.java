package com.ang.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.ang.aop.annotation.OptLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class OptLogAspect {
    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     * 针对使用了@OptLog注解的方法生效
     */
    @Pointcut("@annotation(com.ang.aop.annotation.OptLog)")
    public void optLogPointCut() {

    }

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* com.ang.aop.controller..*.*(..))")
    public void optExceptionLogPointCut() {

    }

    //前置通知
    @Before("optExceptionLogPointCut()")
    public void beforeMethod(JoinPoint joinPoint){
        log.info("调用了前置通知");
    }

    // 后置通知
    @After("optExceptionLogPointCut()")
    public void afterMethod(JoinPoint joinPoint){
        log.info("调用了后置通知");
    }

    //返回通知 result为返回内容
    @AfterReturning(value="optLogPointCut()",returning="result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){

        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作
        OptLog optLog = method.getAnnotation(OptLog.class);
        log.info(String.format("操作模块:%s，操作类型:%s，操作说明:%s",optLog.optModule(),optLog.optType(),optLog.optDesc()));

        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        String methodName = method.getName();

        log.info("方法名："+className+"."+methodName);

        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        // 请求的参数
        Map<String, String> rtnMap = convertMap(request.getParameterMap());
        // 将参数所在的数组转换成json
        String params = JSON.toJSONString(rtnMap);

        log.info("参数："+params);

        log.info("返回值："+result);
    }

    //异常通知
    @AfterThrowing(value="optExceptionLogPointCut()",throwing="e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e){
        log.info(String.format("调用了异常通知:%s",e.getMessage()));
    }

    //环绕通知
    @Around("optExceptionLogPointCut()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around执行方法之前");
        Object object = pjp.proceed();
        log.info("around执行方法之后--返回值：" +object);
        return object;
    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
     public Map<String, String> convertMap(Map<String, String[]> paramMap) {
         Map<String, String> rtnMap = new HashMap<String, String>();
         for (String key : paramMap.keySet()) {
             rtnMap.put(key, paramMap.get(key)[0]);
         }
         return rtnMap;
     }
}
