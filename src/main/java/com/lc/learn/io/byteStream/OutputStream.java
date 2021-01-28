package com.lc.learn.io.byteStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lc 2020-12-01 15:12
 * 字节输出流
 */
@Slf4j
public class OutputStream {

    @Test
    public void testBufferOutputStream() throws IOException {
        log.info("im come in");
        File file = new File("/Users/lc/IdeaProjects/offline-work/src/main/resources/public/test.log");
        BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream(file,true));
        // 要写入的字符串
        String string = "\r 松下问童子，言师采药去。只在此山中，云深不知处。";
        // 写入文件
        bis.write(string.getBytes());
        // 关闭流
        bis.close();

    }

}
