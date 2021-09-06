package com.lc.controller.job;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/jobController")
public class JobController {

    String fileName = "2222.html";

    @PostMapping("/testUpload")
    public String testUpload(@RequestParam("file")MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();

        File f = new File(originalFilename);
        FileUtils.copyInputStreamToFile(file.getInputStream(), f);
        Workbook wb = new Workbook();
        wb.loadFromFile(originalFilename);
//        获取工作表
        Worksheet sheet = wb.getWorksheets().get(0);
//        将工作表保存为HTML
        sheet.saveToHtml(fileName);

        String fileContent = "";
        File htmlFile = new File(fileName);
        if(htmlFile.isFile()&&htmlFile.exists()){
            InputStreamReader read = new InputStreamReader(new FileInputStream(htmlFile),"UTF-8");
            BufferedReader reader=new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                //将读取到的字符拼接
                fileContent += line;
            }
            read.close();
        }
        System.out.println("fileContent:"+fileContent);
        return fileContent;
    }







    @Test
    public void ExcelToHtml() throws IOException {
//        Workbook wb = new Workbook();
//        wb.loadFromFile("/Users/lc/Downloads/杭州南京平台人月项目解决方案梳理-杭州银行.xlsx");
//        wb.saveToFile("/Users/lc/Downloads/asd中阿森纳蛋糕.html", FileFormat.HTML);

        Workbook wb = new Workbook();
        wb.loadFromFile("/Users/lc/Downloads/杭州南京平台人月项目解决方案梳理-杭州银行.xlsx");
        //获取工作表
        Worksheet sheet = wb.getWorksheets().get(0);
        //将工作表保存为HTML
        sheet.saveToHtml(fileName);

//        String br = getHtmlByPageName2(fileName);
//        System.out.println(br);
//        File file = new File(fileName);
//        if (file.isFile() && file.exists()) {
//            file.delete();
//            System.out.println("删除单个文件" + fileName + "成功！");
//        } else {
//            System.out.println("删除单个文件" + fileName + "失败！");
//        }
    }

    private String getHtmlByPageName2(String fileName) throws IOException {
        // /BOOT-INF/classes/templates/dashboard.html
//        String path = "/BOOT-INF/classes/templates/" + pageName + ".html";
        // 返回读取指定资源的输入流
        InputStream is = this.getClass().getResourceAsStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = "";
        StringBuffer sb = new StringBuffer();
        while ((s = br.readLine()) != null) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }


}
