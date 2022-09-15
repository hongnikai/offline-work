package com.lc.learn.java.executor;

/**
 * @author lc 2/22/22 2:46 PM
 */
public class WaitThread extends Thread{

    static Object lock = new Object();
    static int n;
    int i;
    String name;
    WaitThread(String name, int i) {
        this.name = name;
        this.i = i;
    }
    @Override
    public void run() {
        try {
            synchronized (lock) {
                while (i != n) {
                    lock.wait();
                }
                System.out.println(name + " started");
                n++;
                lock.notifyAll();
            }
            synchronized (lock) {
                while (i != n - 4) {
                    lock.wait();
                }
                System.out.println(name + " finished");
                n++;
                lock.notifyAll();
            }
        }
        catch (InterruptedException e) {
        }
    }
    public static void main(String[] args) throws Exception {
        new WaitThread("a", 0).start();
        new WaitThread("b", 1).start();
        new WaitThread("c", 2).start();
        new WaitThread("d", 3).start();
    }


}
