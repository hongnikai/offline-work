package com.lc.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) //定义注解修饰的目标,方法/类x
@Retention(RetentionPolicy.RUNTIME)//定义注解的生命周期(SOURCE源码级别,CLASS编译期级别,RUNTIME运行期级别)
public @interface DoneTime {
    String param() default "测试参数";
}
