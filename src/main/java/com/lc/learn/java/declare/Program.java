package com.lc.learn.java.declare;

/**
 * @author lc 2/3/21 2:36 PM
 */
public class Program {

    public String str = "Hello";

    public Program(String str) {
        this.str = str;
    }

    public Program() {
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
//ctrl + n 构造方法 + getter & setter

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

       Integer a = new Integer(1);
       Integer b = 1;
       int c = 1;
        System.out.println(a==b);
        System.out.println(a==c);




    }



}
