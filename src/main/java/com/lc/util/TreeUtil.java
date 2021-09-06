package com.lc.util;

import com.google.common.collect.Lists;
import com.lc.bo.domain.DataAssetCategoryResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
     * 如果传入的集合存在根结点（实际意义上的根结点fatherId为空）则不需要传入parentId，
     * 但如果传入集合 没有真正意义上的根结点 则需要指定顶部数据的parentId
     * @param list
     * @param parentId  根结点的父id
     * @return
     */
    private List<DataAssetCategoryResponse> convertDataAssetCatrgoryTree(List<DataAssetCategoryResponse> list,String parentId){
        //拼装结果
        List<DataAssetCategoryResponse> treeList = Lists.newArrayList();
        //用来储存节点的子元素map
        Map<Integer,DataAssetCategoryResponse> childMap = new LinkedHashMap<>();
        for (DataAssetCategoryResponse data:list){
            childMap.put(data.getId(),data);
        }
        for (Integer id:childMap.keySet()){
            DataAssetCategoryResponse data = childMap.get(id);
            Integer fatherId = data.getFatherId();
            if(Objects.isNull(fatherId)||fatherId.equals(parentId)){
                //指定根结点
                treeList.add(data);
            }else {
                DataAssetCategoryResponse parentData = childMap.get(fatherId);
                if(CollectionUtils.isEmpty(parentData.getChildren())){
                    parentData.setChildren(Lists.newArrayList());
                }
                parentData.getChildren().add(data);
            }
        }
        return treeList;
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
