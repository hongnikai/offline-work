package com.lc.interviewTest.javase;

import org.junit.Test;

public class TestJob {


    @Test
    public void testInput(){
        int x=0;
        int y=4;
        for (int i = 0; i < 5; i++) {
            String str="";
            for(int k = y;k >= 0 ;k--){
                str+=" ";
            }
            for (int j = 0; j <= x; j++) {
                str+="*";
            }
            System.out.println(str);   //换行
            x+=2;
            y-=1;
        }
    }

    public static void main(String[] args) {
        int sum=0;
        for(int i=1;i<=10;i++) {
            int a1=getSum(i);
            sum=sum+a1;
        }
        System.out.println("sum="+sum);
    }
    public static int getSum(int n) {
        if(n==1) {
            return 1;
        }
        int temp=getSum(n-1);
        return temp*n;
    }
}
