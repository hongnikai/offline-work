package com.lc.learn.java.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


/**
 * @author lc 2021-01-28 10:37
 */
public class TreeSetLearn {

    @Test
    public void learnTreeSet(){
        Set<String> set = new HashSet<String>();
        set.add("xet");
        set.add("jpo");
        set.add("wo");
        set.add("jpo");
        System.out.println(set);

        Set<String> softset = new TreeSet<String>(set);
        System.out.println(softset);
        /**
         * TreeSet集合将数组 按照首字母升序排序
         */
    }







}
