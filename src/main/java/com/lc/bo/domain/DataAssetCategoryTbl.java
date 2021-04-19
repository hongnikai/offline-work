package com.lc.bo.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author lc 4/7/21 10:15 AM
 */
@Data
public class DataAssetCategoryTbl {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 类目编码
     */
    private String categoryCode;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 层级
     */
    private Integer categoryLevel;

    /**
     * 责任人
     */
    private String bearPerson;

    /**
     * 更新人
     */
    private String gmtModifiedOper;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 父节点id
     */
    private Integer fatherId;




}
