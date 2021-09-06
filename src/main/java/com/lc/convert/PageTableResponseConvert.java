package com.lc.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.lc.bo.response.PageTableResponse;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * pagetableResponse 转化类
 */
public class PageTableResponseConvert {


    public static PageTableResponse convert(IPage<?> iPage,Class<?> clazz){
        PageTableResponse pageTableResponse = new PageTableResponse();
        pageTableResponse.setTotalPage(iPage.getPages());
        pageTableResponse.setPage(iPage.getCurrent());
        pageTableResponse.setTotal(iPage.getTotal());
        pageTableResponse.setSize(iPage.getSize());
        pageTableResponse.setList(listConvert(iPage.getRecords(),clazz));
        return pageTableResponse;
    }


    public static <E,T> List<T> listConvert(List<E> source,Class<T> clazz){
        if(CollectionUtils.isNotEmpty(source)){
            List<T> target = new ArrayList<>(source.size());
            source.forEach(s ->{
                T t = BeanUtils.instantiateClass(clazz);
                BeanUtils.copyProperties(s,t);
                target.add(t);
            });
            return target;
        }
        return Lists.newArrayList();
    }

}
