package com.lc.learn.java.arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lc 2021-01-15 15:03
 */
public class ArrayListTest {

    public static void main(String[] args) {

        List<String> names = new ArrayList<String>();
       names.add("tom");
       names.add("jack");


        names = names.subList(0,1);
        System.out.println(names);



    }


}
