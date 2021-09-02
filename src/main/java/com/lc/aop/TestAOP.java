package com.lc.aop;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TestAOP {

    final String test = "abc";
    @org.junit.Test
    @DoneTime(param = "TestAOP")
    public void aopTest(){
        System.out.println("TEST AOP TEST JUNIT");
    }

    @Test
    public void testTryCatch(){

        try {
            System.out.println("进入try");
        }catch (Exception e){
            System.out.println("进入catch");
            throw new RuntimeException();
        }finally {
            System.out.println("最后执行");
        }



    }

    public void test(){}
    public void test(String asd){}

}
