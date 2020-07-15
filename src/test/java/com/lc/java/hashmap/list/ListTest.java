package com.lc.java.hashmap.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListTest {

    @Test
    public void getTest(){
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4));
//        for(String str:list){
//            list.add("6");
//            System.out.println(str);
//        }
        list.stream().forEach(Integer -> {
            System.out.println("asd");
        });




    }

}
