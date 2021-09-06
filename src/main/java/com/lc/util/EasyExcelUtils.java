package com.lc.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import com.lc.bo.domain.DownLoadDTO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class EasyExcelUtils {

    private static final Logger log = LoggerFactory.getLogger(EasyExcelUtils.class);

    /**
     * 判断浏览器类型
     */
    public static String getBrowser(HttpServletRequest request){
        String userAgent = null;
        try {
            userAgent = request.getHeader("USER_AGENT").toLowerCase();
        }catch (Exception e){
            return "FF";
        }
        if(userAgent != null ){
            if (userAgent.indexOf("msie")>= 0){
                return "IE";
            }
            if(userAgent.indexOf("firefox") >= 0){
                return "FF";
            }
            if(userAgent.indexOf("safari") >= 0){
                return "SF";
            }
        }
        return null;
    }

    public static String getFileName(String fileName,HttpServletRequest request){
        try {
            if (EasyExcelUtils.getBrowser(request).equals("FF")){
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
            }else{
                fileName = URLEncoder.encode(fileName,"UTF-8");
            }
        }catch (Exception e){
            log.error("文件名转码错误");
        }
        return fileName;
    }

    /**
     * 生成excel 表格
     */
    public static boolean generateExcel(DownLoadDTO downloadDTO){
        boolean flag = false;/** 下载成功标识*/
        HttpServletResponse response = downloadDTO.getResponse();
        HttpServletRequest request = downloadDTO.getRequest();
        List<String> headersChinese = downloadDTO.getHeadersChinese();
        List<String> headersField = downloadDTO.getHeadersField();
        List list = downloadDTO.getList();
        String fileNmae = downloadDTO.getFilename();
        Class<?> cls = downloadDTO.getCls();
        //组长表头中文名称
        List<List<String>> headList = Lists.newArrayList();
        for (String s: headersChinese){
            List<String> headTitle = Lists.newArrayList();
            headTitle.add(s);
            headList.add(headTitle);
        }
        List<List<Object>> lists = new ArrayList<>(1000000);

        try {
            //中文文件名支持
            String filePath = downloadDTO.getDownloadPath();
            File file = new File(filePath + fileNmae + ".xlsx");
            File fileParent = file.getParentFile();
            String fileParemtPath = file.getParent();
            if(!fileParent.exists()){
                //如果没有文件路径 创建文件夹路径
                fileParent.mkdirs();
            }
            if(!file.exists()){
                file.createNewFile();
            }
            ExcelWriter excelWriter = EasyExcel.write(file).build();

            if(CollectionUtils.isNotEmpty(lists)){
                for (int i=0;i<list.size();i++){
                    List currentList = lists.get(i);
                    WriteSheet writeSheet = EasyExcel.writerSheet(i,"sheetName"+i).head(headList).build();
                    List<List<Object>> valuelist = Lists.newArrayList();
                    for(Object object : currentList){
                        Field[] fields = cls.getDeclaredFields();
                        List<Object> tempList = Lists.newArrayList();
                        for (Field field:fields){
                            String name = field.getName();
                            if(headersField.contains(name)){
                                field.setAccessible(true);
                            }
                            Object o = field.getName();
                            String currentValue = "";
                            if (o instanceof String) {
                                currentValue = String.valueOf(o);
                            } else if (o instanceof Date) {
                                currentValue = DateUtils.format((Date) o);
                            } else if (o instanceof Integer) {
                                currentValue = String.valueOf(o);
                            } else if (o instanceof BigDecimal) {
                                currentValue = Objects.isNull(o) ? "" : String.valueOf(o);
                            }
                            tempList.add(currentValue);
                        }
                        valuelist.add(tempList);
                    }
                    excelWriter.write(valuelist,writeSheet);
                }
            }else{
                WriteSheet writeSheet = EasyExcel
                        .writerSheet(0,"sheetName")
                        .head(headList)
                        .build();
                excelWriter.write(null,writeSheet);
            }
            excelWriter.finish();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            log.error("EasyExcelUtils.generateExcel Exception",e);
        }finally {
            return flag;
        }
    }



}
