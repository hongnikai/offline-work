package com.lc.interviewTest;

import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.zip.GZIPOutputStream;

public class BankInterViewTest extends Thread implements Runnable{

    public void run(){
        System.out.println("this is run()");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new BankInterViewTest());
        t.start();
    }



    @Test
    public void testTrueOrFalse(){
        AAA o = (AAA)Proxy.newProxyInstance(AAA.class.getClassLoader(), new Class[]{AAA.class}, new InvocationHandler() {
            AAA A =new AAAA();
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                before();
                proxy=A;
                return method.invoke(proxy,args);
            }
        });


        o.sayHello();
    }

    public void  before(){
        System.out.println("前置环绕");

    StringBuffer stringBuffer =  new StringBuffer();


    }
    static int arr[] = new int[10];
        @Test
        public void arrInt(){

            System.out.println(arr[1]);


        }



}
