package com.lc.learn.flink.httpClient;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author lc 2020-10-08 02:02
 */
@Slf4j
public class ReptilesUtils {

    @Test
    public void doGet() {
        String url = "https://www.louisvuitton.cn/zhs-cn/homepage";
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);

        try {
            client.executeMethod(method);
        } catch (URIException e) {
            log.error("执行HTTP Get请求时，发生异常！", e);
        } catch (IOException e) {
            log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
        } finally {
            method.releaseConnection();
        }
        log.info("执行HTTP GET请求，返回码: {}", method.getStatusCode());
        System.out.println(method.getStatusCode());
    }


   @Test
    public void httpRequest() {

        String requestUrl = "https://www.louisvuitton.cn/zhs-cn/search/链条包";
          StringBuffer buffer = null;
          BufferedReader bufferedReader = null;
          InputStreamReader inputStreamReader = null;
          InputStream inputStream = null;
          HttpURLConnection httpUrlConn = null;

                 try {
                         // 建立get请求
                         URL url = new URL(requestUrl);
                         httpUrlConn = (HttpURLConnection) url.openConnection();
                         httpUrlConn.setDoInput(true);
                         httpUrlConn.setRequestMethod("GET");
                         // 获取输入流
                         inputStream = httpUrlConn.getInputStream();
                         inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                         bufferedReader = new BufferedReader(inputStreamReader);

                         // 从输入流读取结果
                         buffer = new StringBuffer();
                         String str = null;
                         while ((str = bufferedReader.readLine()) != null) {
                             buffer.append(str);
                         }
                     System.out.println(buffer);
                     } catch (Exception e) {
                        e.printStackTrace();
                       }  finally {
                     // 释放资源
                     if(bufferedReader != null) {
                             try {
                                 bufferedReader.close();
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                         }
                     if(inputStreamReader != null){
                                 try {
                                     inputStreamReader.close();
                                     } catch (IOException e) {
                                         e.printStackTrace();
                                     }
                             }
                         if(inputStream != null){
                                 try {
                                         inputStream.close();
                                     } catch (IOException e) {
                                         e.printStackTrace();
                                     }
                             }
                         if(httpUrlConn != null){
                                 httpUrlConn.disconnect();
                             }
                     }
             }



}
