package com.lc.learn.java.npe;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lc 2/5/21 3:14 PM
 */
public class NullPExcption {

    /**
     * 三目运算空指针的问题
     */
    @Test
    public void condition(){
//        Map<String,Boolean> map =  new HashMap<String, Boolean>();
//        Boolean b = (map!=null ? map.get("test") : false);

        Map<String, Boolean> map = new HashMap<>();
        Boolean b = map != null ? map.get("test") : false;
        System.out.println(map==null);
        System.out.println(b);

//        HashMap hashmap = new HashMap();
//        Boolean boolean1 = Boolean.valueOf(hashmap == null ? false : ((Boolean)hashmap.get("test")).booleanValue());
//        System.out.println(boolean1);
//        这么写会报NPE

//        Map<String,Boolean> map =  new HashMap<String, Boolean>();
//        Boolean b = (map!=null ? map.get("test") : Boolean.FALSE);
        System.out.println(map.get("test")== null);


    }

    @Test
    public void condition2(){
        Integer a = 1;
        Integer b = 2;
        Integer c = null;
        Boolean flag = false;
//        Integer result =(flag?a*b:c);
        System.out.println(flag?a:0);
    }





    /**
     * 判断class引用类型
     * @param clz
     */
    public void isAssignableFrom(Class clz){
        System. out. println (isWrapClass (clz) );
        System. out. println (isWrapClass (clz) );

    }
    public static boolean isWrapClass ( Class clz ) {
        try {
            return ( ( Class ) clz. getField ("TYPE" ). get ( null ) ). isPrimitive ( );
        } catch ( Exception e ) {
            return false;
        }
    }

}
