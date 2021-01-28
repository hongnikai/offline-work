package com.lc.learn.java.hashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author lc 2021-01-26 09:38
 */
public class HashMapTest {


    @Test
    public void learnHashCode(){

        Map<String,String> map = new HashMap<>();
        map.put("abc","abc");
        int i = map.hashCode();
        System.out.println("map hashCode值为 : "+i);


        Map<String,String> map2 = new HashMap<>();
        map.put("abc","abc");
        int i2 = map.hashCode();
        System.out.println("map2 hashCode值为 : "+i2);

        int i3 = HashMapTest.class.hashCode();
        System.out.println("class的hashCode值为 : "+i3);


    }

    @Test
    public void entry(){

        Map<String,String> map = new HashMap<>();
        map.put("abc","abc");
        map.put("xyz","xyz");
        int i = map.hashCode();
        System.out.println("map hashCode值为 : "+i);

        Set keys = map.keySet();
        System.out.println("获取map的Set结果集");
        if(keys != null) {
            Iterator iterator = keys.iterator( );
            while(iterator.hasNext())
            {Object key = iterator.next();
                Object value = map.get(key);
                System.out.println(value);
            }
        }
        /**
         * 然后，这个方法有一个问题。从Map中取得关键字之后，我们必须每次重复返回到Map中取得相对的值，这是很繁琐和费时的。
         * 幸运的是，这里有一个更加简单的途径。Map类提供了一个称为entrySet()的方法，这个方法返回一个Map.Entry实例化后的对象集。
         * 接着，Map.Entry类提供了一个getKey()方法和一个getValue()方法，因此，上面的代码可以被组织得更符合逻辑。举例如下
        */
        Set entries = map.entrySet( );
        if(entries != null) {
            Iterator iterator = entries.iterator();
            while(iterator.hasNext( )) {
                Map.Entry<String,String> entry  = (Map.Entry<String, String>) iterator.next();
                Object key = entry.getKey( );
                Object value = entry.getValue();
                System.out.println("key:"+key);
                System.out.println("value:"+value);
            }
        }

    }

    @Test
    public void initHashMap(){
        /**
         * init hashMap 初始化hashmap 容量为2
         * 添加超过2的元素 hashmap自动扩容
         */
        Map<String,String> map = new HashMap<>(2);
        map.put("aaa","aaa");
        map.put("bbb","bbb");
        map.put("ccc","ccc");
        map.put("ddd","ddd");
        map.put("eee","eee");
        System.out.println(map.entrySet());


    }


}
