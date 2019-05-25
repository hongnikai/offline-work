package com.lc.util;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SuppressWarnings("all")
public class JavaLinux {

    @Test
    public void gotoShell(){

            String[] cmd = { "D:", "", "mkdir test" };
            InputStream in = null;
            String result = null;
            try {
                Process pro = Runtime.getRuntime().exec(cmd);
                pro.waitFor();
                in = pro.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(in));
                while((result = read.readLine())!=null) {
                    System.out.println(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    public static void main(String[] args) {
        String[] cmd = { "/bin/sh", "-c", "netstat -anp " };
        InputStream in = null;
        String result = null;
        try {
            Process pro = Runtime.getRuntime().exec(cmd);
            pro.waitFor();
            in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            while((result = read.readLine())!=null) {
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
