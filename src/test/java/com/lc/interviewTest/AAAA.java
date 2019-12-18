package com.lc.interviewTest;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class AAAA implements AAA {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }

    @Override
    public void sayHello(String s) {
        System.out.println(s);
    }





}
