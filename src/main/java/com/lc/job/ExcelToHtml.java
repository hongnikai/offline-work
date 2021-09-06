package com.lc.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lc 7/13/21 4:01 PM
 */
public class ExcelToHtml {

    private static final Logger logger  = LoggerFactory.getLogger(ExcelToHtml.class);


    /**
     * EXCEL转HTML
     * @param xlsfile EXCEL文件全路径
     * @param htmlfile 转换后HTML存放路径
     */
    public void excelToHtml(String htmlfile,String xlsfile) {
//        ActiveXComponent app = new ActiveXComponent("Excel.Application"); // 启动excel
//        try {
//            app.setProperty("Visible", new Variant(false));
//            Dispatch excels = app.getProperty("Workbooks").toDispatch();
//            Dispatch excel = Dispatch.invoke(excels, "Open", Dispatch.Method, new Object[] { xlsfile, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
//            Dispatch.invoke(excel, "SaveAs", Dispatch.Method, new Object[] { htmlfile, new Variant(44) }, new int[1]);
//            Variant f = new Variant(false);
//            Dispatch.call(excel, "Close", f);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            app.invoke("Quit", new Variant[] {});
//        }
    }

    public static void main(String[] args) {
        String a = "123123.xlsx";
        String str = a.replace(".xlsx",".html");
        str = a.replace(".xls",".html");

        System.out.println(str);

    }


}
