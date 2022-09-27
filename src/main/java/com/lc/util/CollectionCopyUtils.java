package com.lc.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lc 2022/9/15 3:13 PM
 */
public class CollectionCopyUtils {

    public static <E,T> List<T> listConvert(List<E> source,Class<T> clazz){
        if (CollectionUtils.isNotEmpty(source)){
            List<T> target = new ArrayList<>(source.size());
            source.forEach(s->{
                T t = BeanUtils.instantiateClass(clazz);
                BeanUtils.copyProperties(s,t);
                target.add(t);
            });
            return target;
        }
        return Lists.newArrayList();
    }
}
