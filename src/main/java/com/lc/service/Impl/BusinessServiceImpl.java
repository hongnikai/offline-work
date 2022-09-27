package com.lc.service.Impl;

import com.lc.bo.domain.DownLoadDTO;
import com.lc.entity.MagRepIcbcListTbl;
import com.lc.service.BusinessService;
import com.lc.util.EasyExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lc 2022/9/15 2:36 PM
 */
@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {

    /**
     * 简单表头
     */
    @Override
    public void chxBankExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] header = {"序号","数据日期"};
        String[] field = {"num","dataDt"};
        List<MagRepIcbcListTbl> data = new ArrayList<>();//查询数据
        List<MagRepIcbcListTbl> resList = new ArrayList<>();//convert转换数据
        DownLoadDTO downLoadDTO = new DownLoadDTO();
        downLoadDTO.setRequest(request);
        downLoadDTO.setResponse(response);
        downLoadDTO.setFilename("xxxExcel文件");
        downLoadDTO.setList(resList);
        downLoadDTO.setCls(MagRepIcbcListTbl.class);
        downLoadDTO.setHeadersChinese(Arrays.asList(header));
        downLoadDTO.setHeadersField(Arrays.asList(field));
        EasyExcelUtils.downloadExcel(downLoadDTO);
    }

    /**
     * 复杂表头
     */
    private List<List<String>> getHeadersChineseList(){
        List<List<String>> titleList = new ArrayList<>();
        titleList.add(Arrays.asList("机构","机构"));
        titleList.add(Arrays.asList("年月","年月"));
        titleList.add(Arrays.asList("法定工作日","法定工作日"));
        titleList.add(Arrays.asList("客户业务量（加权）","月合计"));
        titleList.add(Arrays.asList("客户业务量（加权）","日均"));
        titleList.add(Arrays.asList("客户业务量（加权）","时jun"));
        return titleList;
    }



}
