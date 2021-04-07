package com.lc.bo.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lc 4/7/21 8:46 AM
 */
@Builder
@Data
public class DataAssetCategoryResponse {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 层级
     */
    private Integer categoryLevel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 父节点id
     */
    private Integer fatherId;

    /**
     * 树节点
     */
    private List<DataAssetCategoryResponse> children;
}
