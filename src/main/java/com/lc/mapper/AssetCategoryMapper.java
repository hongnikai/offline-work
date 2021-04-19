package com.lc.mapper;

import com.lc.bo.domain.DataAssetCategoryTbl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lc 4/7/21 10:28 AM
 */
public interface AssetCategoryMapper {

    List<DataAssetCategoryTbl> getCategoryByConditionReturnOne(String categoryName,String bearPerson);

    Integer insertAssertContent(@Param("tbl") DataAssetCategoryTbl tbl);

    void deleteForList(@Param("list")List<Integer> list);


}
