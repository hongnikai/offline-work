package com.lc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.lc.controller.*.*(..))")
    public void LogAspect(){}

    @Before("LogAspect()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("doBefore");
    }

    @After("LogAspect()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("doAfter");
    }

    @AfterReturning(pointcut = "LogAspect()")
    public void doAfterReturning(JoinPoint joinPoint){
        System.out.println("doAfterReturning");
    }

    @AfterThrowing("LogAspect()")
    public void deAfterThrowing(JoinPoint joinPoint){
        System.out.println("deAfterThrowing");
    }

    @Around("LogAspect()")
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("deAround");
        joinPoint.proceed();
        System.out.println("deAround");
        return null;
    }

}
