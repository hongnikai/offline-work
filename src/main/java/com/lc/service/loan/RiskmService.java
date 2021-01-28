package com.lc.service.loan;

import org.apache.ibatis.annotations.Param;

public interface RiskmService {

    void insertEmoji(@Param("id")String id, @Param("emoji")String emoji);

}
