package com.lc.bo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author lc 6/30/21 3:26 PM
 */
@AllArgsConstructor
@Data
public class DataAssetCategoryResponse implements Serializable ,Comparable<DataAssetCategoryResponse>{

    private String id;//目录编码
    private String catalogName;//目录名称
    private String categoryCode;//类目编码
    private String categoryName;//类目名称
    private String typeId;//资产类型编码
    private String categoryLevel;//层级
    private String bearPerson;//责任人
    private String gmtModifiedOper;//更新人
    private Date gmtCreated;//创建时间
    private Date gmtModified;//更新时间
    private String fatherId;//父节点id
    private Set<String> pids;//父节点id集合
    private String searchCount="0";//检索次数
    private List<DataAssetCategoryResponse> children;//树节点

    @Override
    public int compareTo(DataAssetCategoryResponse o) {
        int thisCount = Integer.parseInt(this.getSearchCount());
        int oCount = Integer.parseInt(o.getSearchCount());
        int i = thisCount - oCount; //按照搜索热度排序
        return i;
    }

    /**
     * 通过id 去重
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o==null||getClass()!=o.getClass()){
            return false;
        }
        DataAssetCategoryResponse dataAssetCategoryResponse = (DataAssetCategoryResponse) o;
        return Objects.equals(id,dataAssetCategoryResponse.getId());
    }

    /**
     * List stream 对象调用distinct() 方法，distinct()方法以来hashCode()和equals()方法
     * 判断两个对象是否相同 原理与HashMap定位key原理相同,先计算hashCode,如果hashCode相同，
     * 继续调用equals()方法
     * @return
     */
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }




}
