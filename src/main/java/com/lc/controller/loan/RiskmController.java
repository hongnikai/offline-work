package com.lc.controller.loan;

import com.lc.service.ExcelOptionsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class RiskmController {




    /**
     * 测试读取excel
     **/
    @Test
//    @RequestMapping("/test/testReadExcel")
    public void testReadExcel() {
        // 这里的excel文件可以 为xls或xlsx结尾
        //
        File file = new File("/Users/lc/IdeaProjects/offline-work/src/main/resources/public/杭州南京平台人月项目解决方案梳理-杭州银行.xlsx");
//        File file = new File("/Users/lc/IdeaProjects/offline-work/src/main/resources/public/现金收支明细表1.xlsx");
        List<List<String>> result = new ArrayList<>();
        try {
            ExcelOptionsService excelOptionsService = new ExcelOptionsService();
            result = excelOptionsService.writeWithoutHead(new FileInputStream(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(result);
        System.out.println("读取结果：" + result);
    }






}
