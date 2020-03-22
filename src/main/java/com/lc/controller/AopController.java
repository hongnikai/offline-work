package com.lc.controller;

import com.lc.aop.DoneTime;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @GetMapping("/index")
    @DoneTime(param = "AopController")
    public String index(){
        System.out.println("方法执行");
        return "hello dalaoyang";
    }

    @GetMapping("/index2")
    public String index2(){
        System.out.println("方法2执行");
        return "hello dalaoyang";
    }

    @Test
    @DoneTime(param = "AopController")
    public void test(){
        System.out.println("方法执行");
    }



}
