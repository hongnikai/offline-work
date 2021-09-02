package com.lc.bo.domain;

import lombok.Data;

@Data
public class TableCommentsDTO {

    /**
     * 表名称
     */
    private String name;

    /**
     * 字段名称 或表
     */
    private String type;

    /**
     * 表或字段注解
     */
    private String comments;

    /**
     * riskm_comm_sort
     */
    private String iscomm;

    private String condition;

}
