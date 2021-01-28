package com.lc.bo.domain;

import lombok.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
/**
 * 下载参数DTO
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DownLoadDTO {

    public HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * 表头中文名称
     */
    private List<String> headersChinese;

    /**
     * 表头字段
     */
    private List<String> headersField;

    /**
     * 数据集合
     */
    private List list;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 下载路径
     */
    private String downloadPath;

    /**
     * 反射对象 例如：BussinessDataTbl.class
     */
    private Class<?> cls;

    private List<TableCommentsDTO> tableCols;


    private List<Map<String,Object>> dataList;

}
