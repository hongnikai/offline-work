package com.lc.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel文件的操作service
 * @author lc 4/1/21 3:45 PM
 */
@Service
public class ExcelOptionsService {


    /**
     * 根据excel输入流，读取excel文件
     *
     * @param inputStream exece表格的输入流
     * @return 返回双重list的集合
     **/
    public List<List<String>> writeWithoutHead(InputStream inputStream) {
        StringExcelListener listener = new StringExcelListener();
        ExcelReader excelReader = EasyExcelFactory.read(inputStream, null, listener).headRowNumber(0).build();
        excelReader.readAll();
        List<List<String>> datas = listener.getDatas();
        excelReader.finish();
        return datas;
    }

    /**
     * StringList 解析监听器
     *
     * @author zhangcanlong
     * @since 2019-10-21
     */
    private static class StringExcelListener extends AnalysisEventListener {

        /**
         * 自定义用于暂时存储data
         * 可以通过实例获取该值
         */

        private List<List<String>> datas = Lists.newArrayList();

        private Integer num;

        private String initSheetName=null;
        /**
         * 每解析一行都会回调invoke()方法
         *
         * @param object  读取后的数据对象
         * @param context 内容
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            @SuppressWarnings("unchecked") Map<String, String> stringMap = (HashMap<String, String>) object;
            // 这里可以获取excel的基本信息，包含excel的总行数
//            System.out.println("不一定十分准确的总行数："+ context.getTotalCount());
            ReadSheetHolder readSheetHolder = context.readSheetHolder();
            num = readSheetHolder.getSheetNo();
            String sheetName = readSheetHolder.getSheetName();
            if(StringUtils.isEmpty(initSheetName)){
//                System.out.println("第一行 sheetname： "+sheetName);
                List<String> sheetList = Lists.newArrayList();
                sheetList.add("第"+num+"页："+sheetName);
                datas.add(sheetList);
            }else if(initSheetName.equals(sheetName)){

            }else{
//                System.out.println("第二页：sheetName"+sheetName);
                List<String> sheetList = Lists.newArrayList();
                sheetList.add("第"+num+"页："+sheetName);
                datas.add(sheetList);
            }
            initSheetName = sheetName;
            //数据存储到list，供批量处理，或后续自己业务逻辑处理。
            datas.add(new ArrayList<>(stringMap.values()));
            //根据自己业务做处理
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            //解析结束销毁不用的资源
            //注意不要调用datas.clear(),否则getDatas为null
            System.out.println("总sheet页数 : "+num);
        }

        /**
         * 返回数据
         *
         * @return 返回读取的数据集合
         **/
        public List<List<String>> getDatas() {
            return datas;
        }

        /**
         * 设置读取的数据集合
         *
         * @param datas 设置读取的数据集合
         **/
        public void setDatas(List<List<String>> datas) {
            this.datas = datas;
        }
    }

}
