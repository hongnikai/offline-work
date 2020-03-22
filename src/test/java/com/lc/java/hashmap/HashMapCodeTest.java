package com.lc.java.hashmap;

import com.lc.interviewTest.AAA;
import com.lc.interviewTest.AAAA;
import com.lc.interviewTest.BankInterViewTest;
import com.lc.interviewTest.X;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class HashMapCodeTest {

    @Test
    public void lookupHashMap(){
        HashMap hashMap = new HashMap();
        // 0000 0001 1
        // 0001 0000 2的四次方 == 16

        int num = 1 << 4;
        System.out.println(num);
    }

    @Test
    public void hashSetAdd(){
        HashSet hashSet = new HashSet();
        hashSet.add("this");
        hashSet.add("this");
        hashSet.add("is");
        //迭代器
        Iterator<Integer> iterator = hashSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
         }
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("that","is");
        hashMap.put("that","my");
        for (String key : hashMap.keySet()){
            System.out.println("key: "+ key + "; value: " + hashMap.get(key));
        }
    }

    private static final Object PRESENT = new Object();
    @Test
    public void hashSetTest(){
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
//        System.out.println(hashMap.put("that","is"));
//        System.out.println(hashMap.put("that",PRESENT));
//        System.out.println(hashMap.put("that","is")==null);
//        System.out.println(hashMap.put("that",PRESENT)==null);

        int h;
        String key = null;
        System.out.println((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16));

    }


}
