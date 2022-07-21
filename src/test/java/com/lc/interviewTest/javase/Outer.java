package com.lc.interviewTest.javase;

import org.apache.ibatis.javassist.runtime.Inner;

import java.io.UnsupportedEncodingException;

public class Outer {

    public void method(){

        new Inner(){

            public void info(){
                System.out.println("inner");
            }
        }.info();

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        new Outer().method();
        String s1 = "你好";
        String s2 = new String(s1.getBytes("GB2312"), "ISO-8859-1");
        System.out.println(s2);
    }

}
