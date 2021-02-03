package com.lc.learn.io.byteStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lc 2020-12-01 14:58
 * 字节输入流
 */
@Slf4j
public class InputStream {


    @Test
    public void testInputStream() throws IOException {
        log.info("读取文件:");
        File file = new File("/Users/lc/IdeaProjects/offline-work/src/main/resources/public/现金收支明细表1.xlsx");
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        // 一次性取多少个字节
        byte[] bytes = new byte[1024];
        // 用来接收读取的字节数组
        StringBuilder sb = new StringBuilder();
        // 读取到的字节数组长度，为-1时表示没有数据
        int length = 0;
        // 循环取数据
        while ((length = fis.read(bytes)) != -1) {
            // 将读取的内容转换成字符串
            sb.append(new String(bytes, 0, length));
        }
        // 关闭流
        fis.close();
        System.out.println(sb.toString());


        File file2 = new File("/Users/lc/IdeaProjects/offline-work/src/main/resources/public/现金收支明细表2.xlsx");
        BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream(file,true));
        // 要写入的字符串
        String string = "\r 松下问童子，言师采药去。只在此山中，云深不知处。";
        // 写入文件
        bis.write(sb.toString().getBytes());
        // 关闭流
        bis.close();

    }

    @Test
    public void testFileinp() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        FileInputStream inputStream=new FileInputStream("/Users/lc/IdeaProjects/offline-work/src/main/java/com/lc/learn/java/set/Set.md");
        FileOutputStream outputStream=new FileOutputStream("/Users/lc/IdeaProjects/offline-work/src/main/java/com/lc/learn/java/hashmap/HashMap.md");
        FileOutputStream outputStreams=new FileOutputStream("/Users/lc/IdeaProjects/offline-work/src/main/java/com/lc/learn/io/byteStream/FileInputStream.md");
        int len;
        byte [] by=new byte[8192];
        while ((len=inputStream.read(by))!=-1){
            outputStream.write(by,0,len);
        }
        if(inputStream.read()==-1){
            Class in=inputStream.getClass();
            Method openo= in.getDeclaredMethod("open0", String.class);
            openo.setAccessible(true);
            openo.invoke(inputStream,"/Users/lc/IdeaProjects/offline-work/src/main/java/com/lc/learn/java/set/Set.md");
        }
        while ((len=inputStream.read(by))!=-1){
            outputStreams.write(by,0,len);
        }
        outputStream.close();
    }


}
