package com.lc.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.lc.bo.domain.DownLoadDTO;
import com.lc.util.excel.CommentCellWriteHandler;
import com.lc.util.excel.CustCellWriteHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EasyExcelUtils {

    private static final Logger log = LoggerFactory.getLogger(EasyExcelUtils.class);


    public static void downloadExcel(DownLoadDTO downLoadDTO) throws IOException {
        HttpServletResponse response = downLoadDTO.getResponse();
        List<String> headersChinese = downLoadDTO.getHeadersChinese();
        List<String> headersField = downLoadDTO.getHeadersField();
        List list = downLoadDTO.getList();
        String fileName = downLoadDTO.getFilename();
        Class<?> cls = downLoadDTO.getCls();
        String comment = downLoadDTO.getComment();
        //组装表头中文名称
        List<List<String>> headList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(headersChinese)) {
            for (String s : headersChinese) {
                List<String> headTitle = new ArrayList<>();
                headTitle.add(s);
                headList.add(headTitle);
            }
        } else {
            //复杂表头导出
            headList = downLoadDTO.getHeadersChineseList();
        }
        //中文文件名支持
        String encodeFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;fileName=" + encodeFileName + ".xlsx");
        response.setHeader("Access-Control-Expose-Headers", "FileName");
        OutputStream out = response.getOutputStream();
        ExcelWriter excelWriter;
        if (StringUtils.isNotEmpty(comment)) {
            excelWriter = EasyExcel.write(out).registerWriteHandler(new CommentCellWriteHandler(comment)).build();
        } else {
            excelWriter = EasyExcel.write(out).registerWriteHandler(new CustCellWriteHandler()).build();
        }
        if (CollectionUtils.isNotEmpty(list)) {
            WriteSheet writeSheet = new WriteSheet();
            writeSheet.setSheetName("1");
            writeSheet.setSheetNo(0);

            WriteTable writeTable = new WriteTable();
            writeTable.setTableNo(1);
            writeTable.setHead(headList);

            List<List<Object>> valueList = new ArrayList<>();
            Field[] fields = cls.getDeclaredFields();
            List<Field> fieldList = new ArrayList<>();
            for (String header : headersField) {
                for (Field field : fields) {
                    String name = field.getName();
                    if (name.equals(header))
                        fieldList.add(field);
                }
            }
            for (Object object : list) {
                if (Objects.nonNull(object)) {
                    List<Object> tempList = new ArrayList<>();
                    for (Field field : fieldList) {
                        field.setAccessible(true);
                        String name = field.getName();
                        //只对比需要导出的字段，即加了ExportField注解的字段
                        if (headersField.contains(name)) {
                            field.setAccessible(true);
                            try {
                                Object o = field.get(object);
                                String currentValue;
                                if (o instanceof Date) {
                                    currentValue = formatExcel((Date) o);
                                    tempList.add(currentValue);
                                }else if(o instanceof BigDecimal){
                                    currentValue = String.valueOf(o);
                                    tempList.add(currentValue);
                                }else if(Objects.isNull(o)){
                                    tempList.add("");
                                }else{
                                    currentValue = String.valueOf(o);
                                    tempList.add(currentValue);
                                }
                            } catch (IllegalAccessException e) {
                                log.error("EasyExcelUtils.downloadExcel Exception", e);
                            }
                        }
                    }
                    valueList.add(tempList);
                }
            }
            excelWriter.write(valueList,writeSheet,writeTable);
        }else{
            WriteSheet writeSheet;
            if(StringUtils.isNotEmpty(comment)){
                writeSheet = EasyExcel.writerSheet(0,"sheetName")
                        .head(headList).registerWriteHandler(new CommentCellWriteHandler(comment)).build();
            }else {
                writeSheet = EasyExcel.writerSheet(0,"sheetName")
                        .head(headList).registerWriteHandler(new CustCellWriteHandler()).build();
            }
            List<List<Object>> valueList = new ArrayList<>();
            excelWriter.write(valueList,writeSheet);
        }
        excelWriter.finish();;
        out.flush();
        out.close();
    }

    private static String formatExcel(Date date) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd HH-mm-ss");
        return format.format(date);
    }

}
