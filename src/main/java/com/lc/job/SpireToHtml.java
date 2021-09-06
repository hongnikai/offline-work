package com.lc.job;

import com.spire.xls.*;
import lombok.Data;
import org.junit.Test;

import java.util.Date;

/**
 * @author lc 4/2/21 10:25 AM
 */
@Data
public class SpireToHtml {

    private String string;
    private Date date;
    private Double doubleData;

    @Test
    public void test(){
        //加载Excel工作簿
        Workbook wb = new Workbook();
        wb.loadFromFile("/Users/lc/Downloads/杭州南京平台人月项目解决方案梳理-杭州银行.xlsx");
        //将整个工作簿保存为html
        wb.saveToFile("/Users/lc/Downloads/asd中阿森纳蛋糕.html", FileFormat.HTML);




    }


}
