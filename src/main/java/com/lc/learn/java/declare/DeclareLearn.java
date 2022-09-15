package com.lc.learn.java.declare;

import java.lang.reflect.Field;

/**
 * java反射机制学习
 * @author lc 2/3/21 2:36 PM
 */
public class DeclareLearn {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Program program = new Program();
        Class progClass = program.getClass();
        //通过反射机制，获取到类Program中的str
        Field strField1 = progClass.getDeclaredField("str");
        //输出private java.lang.String Program.str
        System.out.println(strField1.toString());
        //我想说的是这一点，这里能获取到类Program中的str并对其修改。
        //Program类中没有对str设置的setStr()方法，而且使用了private，
        //这是不允许访问的。
        //但是我们能通过反射机制，从而获取private类型的值，并直接改。
        Field strField = progClass.getDeclaredField("str");
        strField.set(program, "我是Str字符串。");
        //输出：我是Str字符串。
        System.out.println(program.getStr());




    }







}
