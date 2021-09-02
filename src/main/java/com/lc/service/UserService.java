package com.lc.service;

import com.lc.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<Map<String,Object>> selectAllUser();

    List<Map<String,Object>> selectPriceInnerJoinPriceOrder(Map<String, Object> map);

    void insertUsers(List list);

    User findUserByAccountAndPassword(String username, String password);

    User findUserByUserId(String id);

    List<Map<String,Object>> selectForUpdateSock();
}
