package com.lc.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 读取百万级别数据的工具
 * @author lc 4/2/21 10:08 AM
 */
@Slf4j
public class EasyExcelUtil {


    private static final int MAXROWS = 1000000;

    /**
     * 获取默认表头内容的样式
     * @return
     */
    private static HorizontalCellStyleStrategy getDefaultHorizontalCellStyleStrategy(){
        /** 表头样式 **/
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景色（浅灰色）
        // 可以参考：https://www.cnblogs.com/vofill/p/11230387.html
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        // 字体大小
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 10);
        headWriteCellStyle.setWriteFont(headWriteFont);
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        /** 内容样式 **/
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 内容字体样式（名称、大小）
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontName("宋体");
        contentWriteFont.setFontHeightInPoints((short) 10);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //设置内容垂直居中对齐
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置内容水平居中对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置边框样式
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        // 头样式与内容样式合并
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    /**
     * 导出,单个Sheet，最大支持xlsx格式sheet的行数
     * @param response
     * @param data
     * @param fileName
     * @param sheetName
     * @param clazz
     * @throws Exception
     */
    public static void writeExcel(HttpServletResponse response, List<? extends Object> data, String fileName, String sheetName, Class clazz) throws Exception {
        long exportStartTime = System.currentTimeMillis();
        log.info("报表导出Size: "+data.size()+"条。");
        EasyExcel.write(getOutputStream(fileName, response), clazz).excelType(ExcelTypeEnum.XLSX).sheet(sheetName).registerWriteHandler(getDefaultHorizontalCellStyleStrategy()).doWrite(data);

        System.out.println("报表导出结束时间:"+ new Date()+";写入耗时: "+(System.currentTimeMillis()-exportStartTime)+"ms" );
    }

    /**
     * 单一类型大批量数据导出，适用于超过一百万的数据，需要分多个sheet页来导出。自动分页
     * @author QiuYu
     * @createDate 2020-11-16
     * @param response
     * @param data  查询结果
     * @param fileName 导出文件名称
     * @param clazz 映射实体class类
     * @param <T>  查询结果类型
     * @throws Exception
     */
    public static<T> void writeExcel(HttpServletResponse response, List<T> data, String fileName, Class clazz) throws Exception {
        long exportStartTime = System.currentTimeMillis();
        log.info("报表导出Size: "+data.size()+"条。");

        List<List<T>> lists = (List<List<T>>) data;
//        List<List<T>> lists = SplitList.splitList(data,MAXROWS); // 分割的集合

        OutputStream out = getOutputStream(fileName, response);
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(out, clazz).excelType(ExcelTypeEnum.XLSX).registerWriteHandler(getDefaultHorizontalCellStyleStrategy());
        ExcelWriter excelWriter = excelWriterBuilder.build();
        ExcelWriterSheetBuilder excelWriterSheetBuilder;
        WriteSheet writeSheet;
        for (int i =1;i<=lists.size();i++){
            excelWriterSheetBuilder = new ExcelWriterSheetBuilder(excelWriter);
            excelWriterSheetBuilder.sheetNo(i);
            excelWriterSheetBuilder.sheetName("sheet"+i);
            writeSheet = excelWriterSheetBuilder.build();
            excelWriter.write(lists.get(i-1),writeSheet);
        }
        out.flush();
        excelWriter.finish();
        out.close();
        System.out.println("报表导出结束时间:"+ new Date()+";写入耗时: "+(System.currentTimeMillis()-exportStartTime)+"ms" );
    }

    public static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        //  response.setContentType("application/vnd.ms-excel"); // .xls
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // .xlsx
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        return response.getOutputStream();
    }

    /**
     * @author QiuYu
     * @createDate 2020-11-18
     * @param out 输出流
     * @param flag 是否添加默认打印样式，为 true 添加，为 false 不添加。大批量导出去除样式可以节省更多的资源
     * @return
     */
    public static ExcelWriter buildExcelWriter(OutputStream out,Boolean flag)  {
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(out).excelType(ExcelTypeEnum.XLSX);
        if (flag){
            excelWriterBuilder.registerWriteHandler(getDefaultHorizontalCellStyleStrategy());
        }
        return excelWriterBuilder.build();
    }

    /**
     * 默认构建带样式
     * @author QiuYu
     * @createDate 2020-11-18
     * @param out
     * @return
     * @throws Exception
     */
    public static ExcelWriter buildExcelWriter(OutputStream out)  {
        return buildExcelWriter(out,true);
    }

    /**
     * 单纯写入，适用于手动分页
     * @author QiuYu
     * @param excelWriter
     * @param data
     * @param clazz
     * @param sheetNo
     * @param sheetName
     * @param <T>
     * @throws Exception
     */
    public static<T> void writeOnly(ExcelWriter excelWriter, List<T> data, Class clazz, Integer sheetNo, String sheetName) {
        long exportStartTime = System.currentTimeMillis();
        log.info("报表"+sheetNo+"写入Size: "+data.size()+"条。");
        ExcelWriterSheetBuilder excelWriterSheetBuilder;
        WriteSheet writeSheet;
        excelWriterSheetBuilder = new ExcelWriterSheetBuilder(excelWriter);
        excelWriterSheetBuilder.sheetNo(sheetNo);
        excelWriterSheetBuilder.sheetName(sheetName);
        writeSheet = excelWriterSheetBuilder.build();
        writeSheet.setClazz(clazz);
        excelWriter.write(data,writeSheet);
        log.info("报表"+sheetNo+"写入耗时: "+(System.currentTimeMillis()-exportStartTime)+"ms" );
    }


    /**
     * 导出
     * @author QiuYu
     * @param out
     * @param excelWriter
     * @throws IOException
     */
    public static void finishWriter(OutputStream out, ExcelWriter excelWriter) throws IOException {
        out.flush();
        excelWriter.finish();
        out.close();
        System.out.println("报表导出结束时间:"+ new Date());
    }


}
