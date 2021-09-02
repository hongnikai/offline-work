package com.lc.learn.flink.httpClient;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;

import java.io.IOException;

/**
 * @author lc 2020-10-08 01:35
 */
public class AlmanacUtil {

//    @Test
//    public void getSlhz(){
//           String strURL="https://www.baidu.com/s?ie=UTF-8&wd=java%E7%88%AC%E5%8E%BB%E9%A1%B5%E9%9D%A2%E6%95%B0%E6%8D%AE";
//           URL url;
//
//           try{
//                 url = new URL(strURL);
//                 HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
//                 InputStreamReader input=new InputStreamReader(httpConn.getInputStream(),"utf-8");
//
//              BufferedReader buf= new BufferedReader(input);
//
//              String line= "";
//               StringBuilder conf=new StringBuilder();
//               while(buf.readLine()!=null){
//                     conf.append(line);
//                   }
//               System.out.println(conf.append(conf.toString()));
////                 String buf=conf.toString();
////                 int beginIx=buf.indexOf("<ul> <li class=\"cl\"><a href=\"">);
////                 int endIx=buf.indexOf("/" title=\"\"");
////                 String result=buf.substring(beginIx,endIx);
////                 String resl="http://www.forestry.gov.cn"+result.split("href=\"")[1];
//
////               System.out.println(resl);
//               }catch(Exception e){
//                e.printStackTrace();
//
//               }
//         }

    @Test
    public void getdata() {
        String url = "https://www.louisvuitton.cn";
//        String url = "https://blog.csdn.net/donglynn/article/details/47778009";
        String data = null;
        org.apache.commons.httpclient.HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
//        getMethod.setRequestHeader("User_Agent", "Mozilla/5.0(Windows NT 6.1;Win64;x64;rv:39.0) Gecko/20100101 Firefox/39.0");
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());//系统默认的恢复策略
        try {
            int statusCode = client.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("Wrong");
            }
            byte[] responseBody = getMethod.getResponseBody();
            data = new String(responseBody);
//            return data;

        } catch (HttpException e) {
            System.out.println("Please check your provided http address!");
            data = "";
            e.printStackTrace();

        } catch (IOException e) {
            data = "";
            e.printStackTrace();
        } finally {

            getMethod.releaseConnection();

        }
        System.out.println(data);
//        return data;
    }



}
