package com.lc.aop;
import org.junit.Test;
import org.springframework.stereotype.Component;

@Component
public class TestAOP {

    @org.junit.Test
    @DoneTime(param = "TestAOP")
    public void aopTest(){
        System.out.println("TEST AOP TEST JUNIT");
    }

}
