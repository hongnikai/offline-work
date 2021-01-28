package com.lc.service;

import com.lc.service.Impl.FeignOneImpl;
import org.junit.Test;

public class TestExtend extends FeignOneImpl {

    @Test
    public void test(){
        TestExtend testExtend = new TestExtend();
        testExtend.home();


    }

}
