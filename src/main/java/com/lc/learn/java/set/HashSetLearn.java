package com.lc.learn.java.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


/**
 * @author lc 2021-01-28 10:55
 */
public class HashSetLearn {

    private class Student{

        private String name;
        private String age;
        private String grade;
    }


    @Test
    public void learnHashSet(){
        Set<String> set = new HashSet<>();
        set.add("xet");
        set.add("jpo");
        set.add("wo");
        set.add("jpo");
        System.out.println(set);
        /**
         * HashSet 输出数据
         */

    }



}
