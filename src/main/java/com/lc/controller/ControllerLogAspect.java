package com.lc.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author lc 11/4/21 2:02 PM
 */
@Slf4j
@Aspect
@Component
public class ControllerLogAspect {

    @Pointcut("execution(* com.lc.controller..*.*(..))")
    private void controllerPointCut() {
    }

    @Around("controllerPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object proceed = joinPoint.proceed();
        log.info("method:{} cast seconds:{}s", className + "." + methodName, (System.currentTimeMillis() - startTime) / 1000);
        return proceed;
    }

    @AfterThrowing(pointcut = "controllerPointCut()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        log.error("exception from {}, exception message: {}", methodName, ex.getMessage());

    }


}
