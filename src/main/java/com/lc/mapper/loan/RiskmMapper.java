package com.lc.mapper.loan;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RiskmMapper {

    void insertEmoji(@Param("id")String id,@Param("emoji")String emoji);

}
