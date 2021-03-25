package com.lc.learn.io.trans;

import java.io.*;

/**
 * @author lc 2/7/21 11:05 AM
 *
 * transient 关键字学习  不需要序列化的变量用这个标记
 */
public class TransientKeyWords {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        serializeUser();
        deSerializeUser();

    }
    //序列化
    private static void serializeUser() throws IOException {
        User user = new User();
        user.setName("java 架构师");
        user.setAge(23);
        FileOutputStream out = new FileOutputStream("/Users/lc/IdeaProjects/offline-work/src/main/java/com/lc/learn/io/trans/User.md");
        ObjectOutputStream obj = new ObjectOutputStream(out);
        obj.writeObject(user);
        obj.close();
        System.out.println("添加了 transient 序列化 age: = " +user.getAge());
    }
    //反序列化
    private static void deSerializeUser() throws IOException, ClassNotFoundException {
        File file = new File("/Users/lc/IdeaProjects/offline-work/src/main/java/com/lc/learn/io/trans/User.md");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User newUser = (User)ois.readObject();
        System.out.println("添加了 transient 关键字的反序列化: age "+newUser.getAge());

    }

}
