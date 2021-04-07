package com.lc.util;

import com.google.common.collect.Lists;
import com.lc.bo.domain.DataAssetCategoryResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 构建树结构
 * @author lc 4/7/21 8:41 AM
 */
public class TreeUtil {

    public static List<DataAssetCategoryResponse> formatDataTree(List<DataAssetCategoryResponse> list){
        List<DataAssetCategoryResponse> nodeList = Lists.newArrayList();
        for (DataAssetCategoryResponse node1:list){
            boolean mark = false;
            for (DataAssetCategoryResponse node2:list){
                if(node1.getFatherId() != null && node1.getFatherId().equals(node2.getId())){
                    mark = true;
                    if(node2.getChildren() == null){
                        node2.setChildren(Lists.newArrayList());
                    }
                    node2.getChildren().add(node1);
                    break;
                }
            }
            if(!mark){
                nodeList.add(node1);
            }
        }
        return nodeList;
    }

    /**
     * tbl和Res类转化
     * @param data
     * @return
     */
    private List<DataAssetCategoryResponse> converTblToResponeList(List<T> data){
        List<DataAssetCategoryResponse> resData = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(data)){
            data.forEach(d->{
                DataAssetCategoryResponse resDto = DataAssetCategoryResponse.builder().build();
                /**
                 * 类转换
                 */
                BeanUtils.copyProperties(d,resDto);
                resData.add(resDto);
            });
        }

        return resData;
    }


}
