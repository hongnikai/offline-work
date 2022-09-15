package com.lc.bo.domain;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 下载参数DTO
 */
@Data
public class DownLoadDTO {

    public HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * 表头中文名称
     */
    private List<String> headersChinese;

    /**
     * 多表头中文名称
     */
    private List<List<String>> headersChineseList;

    /**
     * 表头字段
     */
    private List<String> headersField;

    /**
     * 多表头字段
     */
    private List<List<String>> headersFieldList;

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

    private List<Class<?>> clsList;

    private List<TableCommentsDTO> tableCols;

    private List<Map<String, Object>> dataList;

    /**
     * 是否多个对象
     */
    private Boolean multiClass;

    private List<String> sheetNames;
    /**
     * 注释
     */
    private String comment;
}
